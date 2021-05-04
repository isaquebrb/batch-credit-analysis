package br.com.isaquebrb.iftm.batchcreditanalysis.job;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.AnalysisValidation;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.*;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis.*;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.validator.ValidatorProcessor;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.AnalysisValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Processors {

    private final ValidatorProcessor valProcessor;
    private final AnalysisValidationService analysisValService;
    private final List<BaseAnalysisProcessor> analysisProcessors;

    public ItemProcessor<CreditAnalysis, CreditAnalysis> documentProcessor() {
        CompositeItemProcessor<CreditAnalysis, CreditAnalysis> compose = new CompositeItemProcessor<>();

        compose.setDelegates(getDocumentProcessorList());

        return compose;
    }

    private List<ItemProcessor<?, ?>> getDocumentProcessorList(){
        List<AnalysisValidationEnum> analysisEnums = analysisValService.findAllByActive(true).stream()
                .map(AnalysisValidation::getName)
                .collect(Collectors.toList());

        List<ItemProcessor<?, ?>> processorList = new ArrayList<>();

        //Validations
        processorList.add(valProcessor.documentValidator());

        //CreditAnalysis -> ProcessPerson
        processorList.add(new AnalysisStartProcessor());

        //Add active processors
        for (BaseAnalysisProcessor processor : analysisProcessors) {
            if (analysisEnums.contains(processor.getEnumName())) {
                processorList.add(processor);
            }
        }
        //ProcessPerson -> CreditAnalysis
        processorList.add(new AnalysisEndProcessor());
        return processorList;
    }
}
