package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import org.springframework.batch.item.ItemProcessor;

public interface AnalysisProcessor extends ItemProcessor<ProcessPerson, ProcessPerson> {

    AnalysisValidationEnum getEnumName();
}
