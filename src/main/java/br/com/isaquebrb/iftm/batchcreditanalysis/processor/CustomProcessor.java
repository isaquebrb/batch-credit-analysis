package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CustomProcessor {

    private final ValidatorProcessor validatorProcessor;

    public ItemProcessor<CreditAnalysis, CreditAnalysis> documentProcessor() {
        CompositeItemProcessor<CreditAnalysis, CreditAnalysis> compose = new CompositeItemProcessor<>();
        compose.setDelegates(Arrays.asList(
                validatorProcessor.documentValidator(),
                new CrednetProcessor(),
                new DataProcessor(),
                new PepProcessor()));
        return compose;
    }
}
