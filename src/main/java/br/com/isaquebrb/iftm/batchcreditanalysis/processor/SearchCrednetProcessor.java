package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.integration.GeneratorCreditInfoClient;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonType;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.Crednet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchCrednetProcessor implements ItemProcessor<CreditAnalysis, Crednet> {

    private final GeneratorCreditInfoClient generatorInfoClient;

    @Override
    public Crednet process(CreditAnalysis item) throws Exception {
        Crednet crednet;

        if (item.getPersonType().equals(PersonType.PF))
            crednet = generatorInfoClient.getCrednetPf(item.getDocument());
        else if (item.getPersonType().equals(PersonType.PJ))
            crednet = generatorInfoClient.getCrednetPj(item.getDocument());
        else
            throw new ValidationException("[]");
        return crednet;
    }
}