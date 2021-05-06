package br.com.isaquebrb.iftm.batchcreditanalysis.processor.search;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.integration.GeneratorCreditInfoClient;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.DocumentRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.CrednetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchCrednetProcessor implements SearchProcessor {

    private final GeneratorCreditInfoClient generatorInfoClient;

    @Override
    public ProcessPerson process(ProcessPerson item) {
        CreditAnalysis creditAnalysis = item.getCreditAnalysis();
        CrednetResponse crednetResponse = getCrednet(creditAnalysis.getPersonType(), creditAnalysis.getDocument());
        item.setCrednetResponse(crednetResponse);
        return item;
    }

    @Override
    public InformationTypeEnum getInfoType() {
        return InformationTypeEnum.CREDNET;
    }

    private CrednetResponse getCrednet(PersonTypeEnum personType, String document) {
        try {
            CrednetResponse crednetResponse = new CrednetResponse();

            if (personType.equals(PersonTypeEnum.PF)) {
                crednetResponse = generatorInfoClient.getCrednetPf(new DocumentRequest(document));
            } else if (personType.equals(PersonTypeEnum.PJ))
                crednetResponse = generatorInfoClient.getCrednetPj(new DocumentRequest(document));

            return crednetResponse;
        } catch (Exception e) {
            throw new SystemException("");
        }
    }
}