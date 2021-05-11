package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.parameter.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet.Bacen;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.MAXIMUM_VALUE_BACEN_PENDENCY;

@Slf4j
@Component
@RequiredArgsConstructor
public class BacenPendencyProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            Bacen bacen = item.getCrednetResponse().getCrednet().getBacen();

            if (bacen.getHasInformation().trim().equalsIgnoreCase("SIM") &&
                    bacen.getContent().getBacenChecks() == null) {
                item.getCreditAnalysis().getProcessingHistory().setValueBacenChecks(BigDecimal.ZERO);
                addStatus(item, MAXIMUM_VALUE_BACEN_PENDENCY, APPROVED);
                return item;
            }

            BigDecimal maxBacenValue = parameterService.getParameter(NumericParameterEnum.MAX_VALUE_BACEN_CHECKS);

            BigDecimal personBacenValue = BigDecimal.valueOf(
                    bacen.getContent().getBacenChecks().stream()
                            .mapToDouble(d -> Double.parseDouble(d.getValue()))
                            .sum()).
                    setScale(2, RoundingMode.HALF_DOWN);

            item.getCreditAnalysis().getProcessingHistory().setValueBacenChecks(personBacenValue);

            if (personBacenValue.compareTo(maxBacenValue) >= 0) {
                throw new BusinessException("O valor de cheques sem fundo (" + personBacenValue + ") ultrapassou o limite (" + maxBacenValue + ")");
            } else {
                addStatus(item, MAXIMUM_VALUE_BACEN_PENDENCY, APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            addStatus(item, MAXIMUM_VALUE_BACEN_PENDENCY, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, MAXIMUM_VALUE_BACEN_PENDENCY, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return MAXIMUM_VALUE_BACEN_PENDENCY;
    }
}
