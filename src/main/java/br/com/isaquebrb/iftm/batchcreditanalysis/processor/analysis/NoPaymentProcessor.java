package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoPaymentProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            if (!item.getCreditAnalysis().getPersonType().equals(PersonTypeEnum.PJ))
                return item;

            BigDecimal personNoPaymentRate = BigDecimal.valueOf(Double.parseDouble(
                    item.getCrednet().getCreditRiskRating().getContent().getNonPayment()));

            BigDecimal maxNoPaymentRate = parameterService.getParameter(NumericParameterEnum.MAX_NO_PAYMENT_RATE);

            item.getProcessingHistory().setNoPaymentRate(personNoPaymentRate);

            if (personNoPaymentRate.compareTo(maxNoPaymentRate) > 0) {
                item.getProcessingHistory().setNoPaymentAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            } else {
                throw new BusinessException("A taxa de inadimplência (" + personNoPaymentRate + ") ultrapassou o limite (" + maxNoPaymentRate + ")");
            }
        } catch (BusinessException e) {
            log.warn("[NoPaymentProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setNoPaymentAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[NoPaymentProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setNoPaymentAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MAXIMUM_RATE_NON_PAYMENT;
    }
}
