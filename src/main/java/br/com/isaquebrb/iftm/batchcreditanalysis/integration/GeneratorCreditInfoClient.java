package br.com.isaquebrb.iftm.batchcreditanalysis.integration;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.CrednetPf;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.CrednetPj;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "generatorCreditInfoClient", url = "${generator.credit.info.url}")
public interface GeneratorCreditInfoClient {

    @GetMapping(value = "crednet/pf/{document}")
    CrednetPf getCrednetPf(@PathVariable String document);

    @GetMapping(value = "crednet/pj/{document}")
    CrednetPj getCrednetPj(@PathVariable String document);
}
