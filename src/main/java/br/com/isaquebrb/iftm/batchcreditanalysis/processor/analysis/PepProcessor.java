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
public class PepProcessor implements AnalysisProcessor {

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            if (!item.getCreditAnalysis().getPersonType().equals(PersonTypeEnum.PF))
                return item;

            boolean isPep = item.getPepResponse().getPep().getPepConfirmation().trim()
                    .equalsIgnoreCase("SIM");

            item.getProcessingHistory().setIsPep(isPep);

            if (isPep) {
                throw new BusinessException("É uma pessoa politicamente exposta");
            } else {
                item.getProcessingHistory().setPepAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            log.warn("[PepProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setPepAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[PepProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setPepAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.IS_PEP;
    }
}
