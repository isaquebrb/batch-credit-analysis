package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DocumentSituationProcessor implements ItemProcessor<ProcessPerson, ProcessPerson> {
    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        return null;
    }
}
