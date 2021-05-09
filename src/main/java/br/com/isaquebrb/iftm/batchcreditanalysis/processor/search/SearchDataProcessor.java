package br.com.isaquebrb.iftm.batchcreditanalysis.processor.search;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.integration.GeneratorCreditInfoClient;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.DocumentRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.DataResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data.Data;
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

        setData(item, dataResponse);

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

    private void setData(ProcessPerson item, DataResponse dataResponse) {
        //set data response
        item.setDataResponse(dataResponse);

        Data data = item.getDataResponse().getData();

        if (data == null)
            return;

        //set email address
        if (data.getEmails() != null && data.getEmails().getContent() != null) {
            data.getEmails().getContent().getEmailAddresses()
                    .stream()
                    .findFirst()
                    .ifPresent(email -> item.getCreditAnalysis()
                            .setEmail(email.getEmailAddress()));
        }

        //set phone number
        if (data.getPhonesSearch() != null && data.getPhonesSearch().getContent() != null) {
            data.getPhonesSearch().getContent().getCellphones()
                    .stream()
                    .findFirst()
                    .ifPresent(c -> item.getCreditAnalysis()
                            .setPhoneNumber(c.getAreaCode().trim() + c.getPhoneNumber().trim()));
        }
    }
}
