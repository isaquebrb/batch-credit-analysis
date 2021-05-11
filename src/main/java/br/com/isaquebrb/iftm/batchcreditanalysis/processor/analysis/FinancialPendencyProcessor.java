package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.parameter.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet.FinancialPendency;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.MAXIMUM_VALUE_FINANCIAL_PENDENCY;

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
                item.getCreditAnalysis().getProcessingHistory().setValueFinancialPendencies(BigDecimal.ZERO);
                addStatus(item, MAXIMUM_VALUE_FINANCIAL_PENDENCY, APPROVED);
                return item;
            }

            BigDecimal maxPendencyValue = parameterService.getParameter(NumericParameterEnum.MAX_VALUE_FINANCIAL_PENDENCY);

            BigDecimal personPendencyValue = BigDecimal.valueOf(
                    Double.parseDouble(pendency.getContent().getFinancialPendencyDetail().getValue()))
                    .setScale(2, RoundingMode.HALF_DOWN);

            item.getCreditAnalysis().getProcessingHistory().setValueFinancialPendencies(personPendencyValue);

            if (personPendencyValue.compareTo(maxPendencyValue) > 0) {
                throw new BusinessException("O valor de pendencias financeiras (" + personPendencyValue
                        + ") ultrapassou o limite (" + maxPendencyValue + ")");
            } else {
                addStatus(item, MAXIMUM_VALUE_FINANCIAL_PENDENCY, APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            addStatus(item, MAXIMUM_VALUE_FINANCIAL_PENDENCY, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, MAXIMUM_VALUE_FINANCIAL_PENDENCY, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return MAXIMUM_VALUE_FINANCIAL_PENDENCY;
    }
}