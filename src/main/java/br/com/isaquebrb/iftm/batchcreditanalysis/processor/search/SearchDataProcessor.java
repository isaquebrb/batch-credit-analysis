package br.com.isaquebrb.iftm.batchcreditanalysis.processor.search;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.integration.GeneratorCreditInfoClient;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.DocumentRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchDataProcessor implements SearchProcessor {

    private final GeneratorCreditInfoClient generatorInfoClient;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        CreditAnalysis analysis = item.getCreditAnalysis();
        DataResponse dataResponse = getData(analysis.getPersonType(), analysis.getDocument());
        item.setDataResponse(dataResponse);
        return item;
    }

    @Override
    public InformationTypeEnum getInfoType() {
        return InformationTypeEnum.DATA;
    }

    private DataResponse getData(PersonTypeEnum personType, String document) {
        try {
            DataResponse dataResponse = new DataResponse();

            if (personType.equals(PersonTypeEnum.PF))
                dataResponse = generatorInfoClient.getDataPf(new DocumentRequest(document));
            else if (personType.equals(PersonTypeEnum.PJ))
                dataResponse = generatorInfoClient.getDataPj(new DocumentRequest(document));

            return dataResponse;
        } catch (Exception e) {
            throw new SystemException("");
        }
    }
}
