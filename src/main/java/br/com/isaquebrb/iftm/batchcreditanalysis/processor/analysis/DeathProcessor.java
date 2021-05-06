package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeathProcessor implements AnalysisProcessor {

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            if (item.getCreditAnalysis().getPersonType().equals(PersonTypeEnum.PJ))
                return item;

            boolean isDead = item.getCrednet().getDeath().getContent().getMessage().trim()
                    .equalsIgnoreCase("OBITO");

            item.getProcessingHistory().setIsDead(isDead);

            if (isDead) {
                throw new BusinessException("Informação de óbito constada");
            } else {
                item.getProcessingHistory().setDeathAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            log.warn("[DeathProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setDeathAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[DeathProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setDeathAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.DEATH;
    }
}
