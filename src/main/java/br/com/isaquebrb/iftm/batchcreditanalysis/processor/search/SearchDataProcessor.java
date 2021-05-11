package br.com.isaquebrb.iftm.batchcreditanalysis.processor.search;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.integration.GeneratorCreditInfoClient;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.DocumentRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.DataResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data.Data;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchDataProcessor implements SearchProcessor {

    private final GeneratorCreditInfoClient generatorInfoClient;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            CreditAnalysis analysis = item.getCreditAnalysis();

            DataResponse dataResponse = getData(analysis.getPersonType(), analysis.getDocument());

            setData(item, dataResponse);

            return item;
        } catch (Exception e) {
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            item.getCreditAnalysis().setStatus(AnalysisStatusEnum.ERROR);
            throw e;
        }

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
        } catch (FeignException e) {
            log.error("[SearchDataProcessor.process] {}", buildFeignErrorMsg(e));
            throw new SystemException("Erro ao buscar informacao DATA (dados pessoais)");
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
