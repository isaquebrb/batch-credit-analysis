package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class SearchPepProcessor implements SearchProcessor {

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        return null;
    }

    @Override
    public InformationTypeEnum getInfoType() {
        return InformationTypeEnum.PEP;
    }
}
