package br.com.isaquebrb.iftm.batchcreditanalysis.processor.search;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.integration.GeneratorCreditInfoClient;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.InformationTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.DocumentRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.PepResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchPepProcessor implements SearchProcessor {

    private final GeneratorCreditInfoClient generatorInfoClient;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        if (item.getCreditAnalysis().getPersonType().equals(PersonTypeEnum.PJ))
            return item;

        try {
            PepResponse pepResponse = generatorInfoClient.getPep(new DocumentRequest(
                    item.getCreditAnalysis().getDocument()));

            item.setPepResponse(pepResponse);
            return item;
        } catch (FeignException e) {
            item.getCreditAnalysis().setStatus(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            log.error("[SearchPepProcessor.process] {}", buildFeignErrorMsg(e));
            throw new SystemException("Erro ao buscar informacao PEP");
        }
    }

    @Override
    public InformationTypeEnum getInfoType() {
        return InformationTypeEnum.PEP;
    }
}
