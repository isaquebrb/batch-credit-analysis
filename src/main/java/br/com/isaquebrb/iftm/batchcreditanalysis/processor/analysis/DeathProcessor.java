package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.DEATH;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeathProcessor implements AnalysisProcessor {

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            if (item.getCreditAnalysis().getPersonType().equals(PersonTypeEnum.PJ)){
                addStatus(item, DEATH, APPROVED);
                return item;
            }


            boolean isDead = item.getCrednetResponse().getCrednet().getDeath().getContent().getMessage().trim()
                    .equalsIgnoreCase("OBITO");

            item.getCreditAnalysis().getProcessingHistory().setIsDead(isDead);

            if (isDead) {
                throw new BusinessException("Informacao de obito constada");
            } else {
                addStatus(item, DEATH, APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            addStatus(item, DEATH, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, DEATH, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return DEATH;
    }
}
