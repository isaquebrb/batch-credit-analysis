package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import org.springframework.batch.item.ItemProcessor;

import java.util.Random;

public class SearchDataProcessor implements ItemProcessor<CreditAnalysis, CreditAnalysis> {

    @Override
    public CreditAnalysis process(CreditAnalysis item) throws Exception {
        if (new Random().nextBoolean()) {
            throw new BusinessException("ERRO MALDITO");
        }
        item.setPhoneNumber("34997755122");
        return item;
    }
}
