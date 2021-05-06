package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet.StateProtest;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
                item.getProcessingHistory().setValueStateProtests(BigDecimal.ZERO);
                item.getProcessingHistory().setStateProtestAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }

            BigDecimal maxProtestsValue = parameterService.getParameter(NumericParameterEnum.MAX_VALUE_STATE_PROTEST);

            BigDecimal personProtestsValue = BigDecimal.valueOf(Double.parseDouble(protest.getContent().getValue()));

            item.getProcessingHistory().setValueStateProtests(personProtestsValue);

            if (personProtestsValue.compareTo(maxProtestsValue) > 0) {
                throw new BusinessException("O valor dos protestos estaduais (" + personProtestsValue +
                        ") ultrapassou o limite (" + maxProtestsValue + ")");
            } else {
                item.getProcessingHistory().setStateProtestAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            log.warn("[StateProtestProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setStateProtestAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[StateProtestProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setStateProtestAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MAXIMUM_VALUE_STATE_PROTEST;
    }
}
