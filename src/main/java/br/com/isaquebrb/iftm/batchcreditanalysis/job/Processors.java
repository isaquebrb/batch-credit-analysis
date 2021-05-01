package br.com.isaquebrb.iftm.batchcreditanalysis.job;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.SearchCrednetProcessor;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.SearchDataProcessor;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.SearchPepProcessor;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.validator.ValidatorProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Processors {

    private final ValidatorProcessor validatorProcessor;
    private final SearchCrednetProcessor searchCrednetProcessor;

    public ItemProcessor<CreditAnalysis, CreditAnalysis> documentProcessor() {
        CompositeItemProcessor<CreditAnalysis, CreditAnalysis> compose = new CompositeItemProcessor<>();
        compose.setDelegates(Arrays.asList(
                validatorProcessor.documentValidator(),
                searchCrednetProcessor,
                new SearchDataProcessor(),
                new SearchPepProcessor()));
        return compose;
    }
}
