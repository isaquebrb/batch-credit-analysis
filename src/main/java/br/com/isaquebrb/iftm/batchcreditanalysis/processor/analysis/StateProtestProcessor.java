package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet.StateProtest;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.MAXIMUM_VALUE_STATE_PROTEST;

@Slf4j
@Component
@RequiredArgsConstructor
public class StateProtestProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            StateProtest protest = item.getCrednetResponse().getCrednet().getStateProtest();

            if (protest.getHasInformation().trim().equalsIgnoreCase("SIM") &&
                    protest.getContent().getValue() == null) {
                item.getCreditAnalysis().getProcessingHistory().setValueStateProtests(BigDecimal.ZERO);
                addStatus(item, MAXIMUM_VALUE_STATE_PROTEST, APPROVED);
                return item;
            }

            BigDecimal maxProtestsValue = parameterService.getParameter(NumericParameterEnum.MAX_VALUE_STATE_PROTEST);

            BigDecimal personProtestsValue = BigDecimal.valueOf(Double.parseDouble(protest.getContent().getValue()))
                    .setScale(2, RoundingMode.HALF_DOWN);

            item.getCreditAnalysis().getProcessingHistory().setValueStateProtests(personProtestsValue);

            if (personProtestsValue.compareTo(maxProtestsValue) > 0) {
                throw new BusinessException("O valor dos protestos estaduais (" + personProtestsValue +
                        ") ultrapassou o limite (" + maxProtestsValue + ")");
            } else {
                addStatus(item, MAXIMUM_VALUE_STATE_PROTEST, APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            addStatus(item, MAXIMUM_VALUE_STATE_PROTEST, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, MAXIMUM_VALUE_STATE_PROTEST, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return MAXIMUM_VALUE_STATE_PROTEST;
    }
}
