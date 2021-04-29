package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.PersonType;
import org.springframework.batch.item.ItemProcessor;

public class PepProcessor implements ItemProcessor<CreditAnalysis, CreditAnalysis> {

    @Override
    public CreditAnalysis process(CreditAnalysis item) throws Exception {
        item.setPersonType(PersonType.PF);
        return item;
    }
}
