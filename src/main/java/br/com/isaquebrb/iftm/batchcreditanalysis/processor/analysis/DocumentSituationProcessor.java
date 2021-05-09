package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.StringParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.DOCUMENT_SITUATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentSituationProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            String docSituation = parameterService.getParameter(StringParameterEnum.RF_DOC_SITUATION);
            String personDocSituation = item.getCrednetResponse().getCrednet().getPersonInfo().getContent().getDocSituation().trim();

            item.getCreditAnalysis().getProcessingHistory().setDocumentSituation(personDocSituation);

            if (personDocSituation.equals(docSituation)) {
                addStatus(item, DOCUMENT_SITUATION, APPROVED);
                return item;
            } else {
                throw new BusinessException("A situacao do documento na receita federal e invalida (" + personDocSituation + "). Valido = " + docSituation);
            }
        } catch (BusinessException e) {
            addStatus(item, DOCUMENT_SITUATION, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, DOCUMENT_SITUATION, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return DOCUMENT_SITUATION;
    }
}
