package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import org.springframework.batch.item.ItemProcessor;

public interface BaseAnalysisProcessor extends ItemProcessor<ProcessPerson, ProcessPerson> {

    AnalysisValidationEnum getEnumName();
}
