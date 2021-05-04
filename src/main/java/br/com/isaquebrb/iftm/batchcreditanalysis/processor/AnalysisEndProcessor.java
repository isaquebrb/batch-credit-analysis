package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import org.springframework.batch.item.ItemProcessor;

public class AnalysisEndProcessor implements ItemProcessor<ProcessPerson, CreditAnalysis> {

    @Override
    public CreditAnalysis process(ProcessPerson item) throws Exception {
        return item.getCreditAnalysis();
    }
}
