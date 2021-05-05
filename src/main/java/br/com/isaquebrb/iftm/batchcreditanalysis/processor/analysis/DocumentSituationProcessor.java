package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.StringParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentSituationProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            String docSituation = parameterService.getParameter(StringParameterEnum.RF_DOC_SITUATION);
            String personDocSituation = item.getCrednet().getPersonInfo().getContent().getDocSituation().trim();

            item.getProcessingHistory().setDocumentSituation(personDocSituation);

            if (personDocSituation.equals(docSituation)) {
                item.getProcessingHistory().setDocumentSituationAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            } else {
                throw new BusinessException("A situação do documento na receita federal é inválida (" + personDocSituation + "). Válido = " + docSituation);
            }
        } catch (BusinessException e) {
            log.warn("[DocumentSituationProcessor.process] Documento {} {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setDocumentSituationAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[DocumentSituationProcessor.process] Documento {} {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setDocumentSituationAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.RF_DOCUMENT_SITUATION;
    }
}
