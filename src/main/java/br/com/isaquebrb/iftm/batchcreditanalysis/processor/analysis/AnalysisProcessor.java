package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import org.springframework.batch.item.ItemProcessor;

public interface AnalysisProcessor extends ItemProcessor<ProcessPerson, ProcessPerson> {

    AnalysisValidationEnum getEnumName();

    default void addStatus(ProcessPerson person, AnalysisValidationEnum processor, AnalysisStatusEnum status) {
        person.getCreditAnalysis().getProcessingHistory().getProcessorsStatus().put(processor, status);
        person.getCreditAnalysis().setStatus(status);
    }
}
