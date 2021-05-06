package br.com.isaquebrb.iftm.batchcreditanalysis.integration;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.CrednetResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.DataResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet.Crednet;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data.Data;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.DocumentRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.PepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "generatorCreditInfoClient", url = "${generator.credit.info.url}")
public interface GeneratorCreditInfoClient {

    @PostMapping(value = "crednet/pf")
    CrednetResponse getCrednetPf(DocumentRequest document);

    @PostMapping(value = "crednet/pj")
    CrednetResponse getCrednetPj(DocumentRequest document);

    @PostMapping(value = "data/pf")
    DataResponse getDataPf(DocumentRequest document);

    @PostMapping(value = "data/pj")
    DataResponse getDataPj(DocumentRequest document);

    @PostMapping(value = "pep")
    PepResponse getPep(DocumentRequest document);
}
