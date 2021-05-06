package br.com.isaquebrb.iftm.batchcreditanalysis.integration;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.Crednet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "generatorCreditInfoClient", url = "${generator.credit.info.url}")
public interface GeneratorCreditInfoClient {

    @GetMapping(value = "crednet/pf/{document}")
    Crednet getCrednetPf(@PathVariable String document);

    @GetMapping(value = "crednet/pj/{document}")
    Crednet getCrednetPj(@PathVariable String document);
}
