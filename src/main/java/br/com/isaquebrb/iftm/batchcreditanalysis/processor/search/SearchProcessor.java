package br.com.isaquebrb.iftm.batchcreditanalysis.processor.search;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import org.springframework.batch.item.ItemProcessor;

public interface SearchProcessor extends ItemProcessor<ProcessPerson, ProcessPerson> {
    InformationTypeEnum getInfoType();
}
