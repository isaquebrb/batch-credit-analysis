package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import org.springframework.batch.item.ItemProcessor;

public class DataProcessor implements ItemProcessor<CreditAnalysis, CreditAnalysis> {

    @Override
    public CreditAnalysis process(CreditAnalysis item) throws Exception {
        item.setCellphoneNumber("34997755122");
        return item;
    }
}
