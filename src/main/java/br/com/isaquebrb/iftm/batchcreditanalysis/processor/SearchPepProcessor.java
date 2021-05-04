package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SearchPepProcessor implements ItemProcessor<CreditAnalysis, CreditAnalysis> {

    @Override
    public CreditAnalysis process(CreditAnalysis item) throws Exception {
        item.setPersonType(PersonTypeEnum.PF);
        return item;
    }
}
