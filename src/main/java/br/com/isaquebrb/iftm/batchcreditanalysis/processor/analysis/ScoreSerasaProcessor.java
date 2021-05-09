package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.IntegerParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.MINIMUM_RATE_SCORE_SERASA;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScoreSerasaProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            Integer minSerasaScore = parameterService.getParameter(IntegerParameterEnum.MIN_SCORE_SERASA);

            Integer personScoreSerasa = Integer.parseInt(
                    item.getCrednetResponse().getCrednet().getScoreSerasa().getContent().getScore());

            item.getCreditAnalysis().getProcessingHistory().setScoreSerasa(personScoreSerasa);

            if (personScoreSerasa > minSerasaScore) {
                addStatus(item, MINIMUM_RATE_SCORE_SERASA, APPROVED);
                return item;
            } else {
                throw new BusinessException("Score serasa menor (" + personScoreSerasa
                        + ") que o valor minimo (" + minSerasaScore + ")");
            }
        } catch (BusinessException e) {
            addStatus(item, MINIMUM_RATE_SCORE_SERASA, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, MINIMUM_RATE_SCORE_SERASA, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return MINIMUM_RATE_SCORE_SERASA;
    }
}
