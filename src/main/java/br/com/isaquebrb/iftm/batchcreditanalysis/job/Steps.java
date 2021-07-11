package br.com.isaquebrb.iftm.batchcreditanalysis.job;

import br.com.isaquebrb.iftm.batchcreditanalysis.listener.CreditAnalysisListener;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Steps {

    private final StepBuilderFactory stepBuilder;
    private final Readers readers;
    private final Processors processors;
    private final CreditAnalysisWriter creditAnalysisWriter;
    private final CreditAnalysisListener creditAnalysisListener;

    public Step processFileStep(String pathFile) {
        return stepBuilder.get("processFileStep")
                .<CreditAnalysis, CreditAnalysis>chunk(100)
                .reader(readers.documentReader(pathFile))
                .processor(processors.documentProcessor())
                .writer(creditAnalysisWriter)
                .listener(creditAnalysisListener)
                .faultTolerant()
                .skipPolicy(new CustomSkipPolicy())
                .build();
    }
}
