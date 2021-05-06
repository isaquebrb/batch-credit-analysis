package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet.FinancialPendency;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinancialPendencyProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            FinancialPendency pendency = item.getCrednetResponse().getCrednet().getFinancialPendency();

            if (pendency.getHasInformation().trim().equalsIgnoreCase("SIM") &&
                    pendency.getContent().getFinancialPendencyDetail() == null) {
                item.getProcessingHistory().setValueFinancialPendencies(BigDecimal.ZERO);
                item.getProcessingHistory().setFinancialPendencyAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }

            BigDecimal maxPendencyValue = parameterService.getParameter(NumericParameterEnum.MAX_VALUE_FINANCIAL_PENDENCY);

            BigDecimal personPendencyValue = BigDecimal.valueOf(
                    Double.parseDouble(pendency.getContent().getFinancialPendencyDetail().getValue()));

            item.getProcessingHistory().setValueFinancialPendencies(personPendencyValue);

            if (personPendencyValue.compareTo(maxPendencyValue) > 0) {
                throw new BusinessException("O valor de pendências financeiras (" + personPendencyValue
                        + ") ultrapassou o limite (" + maxPendencyValue + ")");
            } else {
                item.getProcessingHistory().setFinancialPendencyAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            log.warn("[FinancialPendencyProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setFinancialPendencyAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[FinancialPendencyProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setFinancialPendencyAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MAXIMUM_VALUE_FINANCIAL_PENDENCY;
    }
}