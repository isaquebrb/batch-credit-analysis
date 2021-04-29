package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CrednetProcessor implements ItemProcessor<CreditAnalysis, CreditAnalysis> {

    @Override
    public CreditAnalysis process(CreditAnalysis item) throws Exception {
        item.setEmail("email@email");
        return item;
    }
}
