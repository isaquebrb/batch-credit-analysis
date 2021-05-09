package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;

public class AnalysisStartProcessor implements ItemProcessor<CreditAnalysis, ProcessPerson> {

    @Override
    public ProcessPerson process(CreditAnalysis item) throws Exception {
        ProcessPerson processPerson = new ProcessPerson();
        item.setStartDate(LocalDateTime.now());
        processPerson.setCreditAnalysis(item);
        return processPerson;
    }
}
