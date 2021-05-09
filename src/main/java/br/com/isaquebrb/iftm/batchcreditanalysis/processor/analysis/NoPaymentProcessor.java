package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.MAXIMUM_RATE_NON_PAYMENT;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoPaymentProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            if (!item.getCreditAnalysis().getPersonType().equals(PersonTypeEnum.PJ)) {
                addStatus(item, MAXIMUM_RATE_NON_PAYMENT, APPROVED);
                return item;
            }

            BigDecimal personNoPaymentRate = BigDecimal.valueOf(Double.parseDouble(
                    item.getCrednetResponse().getCrednet().getCreditRiskRating().getContent().getNonPayment()));

            BigDecimal maxNoPaymentRate = parameterService.getParameter(NumericParameterEnum.MAX_NO_PAYMENT_RATE);

            item.getCreditAnalysis().getProcessingHistory().setNoPaymentRate(personNoPaymentRate);

            if (personNoPaymentRate.compareTo(maxNoPaymentRate) > 0) {
                addStatus(item, MAXIMUM_RATE_NON_PAYMENT, APPROVED);
                return item;
            } else {
                throw new BusinessException("A taxa de inadimplência (" + personNoPaymentRate
                        + ") ultrapassou o limite (" + maxNoPaymentRate + ")");
            }
        } catch (BusinessException e) {
            addStatus(item, MAXIMUM_RATE_NON_PAYMENT, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, MAXIMUM_RATE_NON_PAYMENT, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return MAXIMUM_RATE_NON_PAYMENT;
    }
}
