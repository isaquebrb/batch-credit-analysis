package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.integration.GeneratorCreditInfoClient;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.Crednet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchCrednetProcessor implements SearchProcessor {

    private final GeneratorCreditInfoClient generatorInfoClient;

    @Override
    public ProcessPerson process(ProcessPerson item) {
        CreditAnalysis analysis = item.getCreditAnalysis();

        Crednet crednet;

        if (analysis.getPersonType().equals(PersonTypeEnum.PF))
            crednet = generatorInfoClient.getCrednetPf(analysis.getDocument());
        else if (analysis.getPersonType().equals(PersonTypeEnum.PJ))
            crednet = generatorInfoClient.getCrednetPj(analysis.getDocument());
        else
            throw new ValidationException("");

        item.setCrednet(crednet);
        return item;
    }

    @Override
    public InformationTypeEnum getInfoType() {
        return InformationTypeEnum.CREDNET;
    }
}