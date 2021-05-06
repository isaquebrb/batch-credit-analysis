package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.IntegerParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

            item.getProcessingHistory().setScoreSerasa(personScoreSerasa);

            if (personScoreSerasa > minSerasaScore) {
                item.getProcessingHistory().setScoreSerasaAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            } else {
                throw new BusinessException("Score serasa menor (" + personScoreSerasa
                        + ") que o valor mínimo (" + minSerasaScore + ")");
            }
        } catch (BusinessException e) {
            log.warn("[ScoreSerasaProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setScoreSerasaAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[ScoreSerasaProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setScoreSerasaAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MINIMUM_RATE_SCORE_SERASA;
    }
}
