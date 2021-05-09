package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.IS_PEP;

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

            item.getCreditAnalysis().getProcessingHistory().setIsPep(isPep);

            if (isPep) {
                throw new BusinessException("E uma pessoa politicamente exposta");
            } else {
                item.getCreditAnalysis().getProcessingHistory().getProcessorsStatus().put(IS_PEP, APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            addStatus(item, IS_PEP, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, IS_PEP, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return IS_PEP;
    }
}
