package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PepProcessor implements ItemProcessor<ProcessPerson, ProcessPerson> {

    public static final InformationTypeEnum INFO_TYPE = InformationTypeEnum.PEP;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        return null;
    }
}
