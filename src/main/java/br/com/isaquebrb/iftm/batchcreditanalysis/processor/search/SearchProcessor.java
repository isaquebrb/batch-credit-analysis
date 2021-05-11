package br.com.isaquebrb.iftm.batchcreditanalysis.processor.search;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import feign.FeignException;
import org.springframework.batch.item.ItemProcessor;

public interface SearchProcessor extends ItemProcessor<ProcessPerson, ProcessPerson> {
    InformationTypeEnum getInfoType();

    default String buildFeignErrorMsg(FeignException e) {
        return "Request: " + e.request().toString() + "\nErro: " + e.getMessage();
    }
}
