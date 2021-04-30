package br.com.isaquebrb.iftm.batchcreditanalysis.step;

import br.com.isaquebrb.iftm.batchcreditanalysis.listener.ProcessorListener;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.CustomProcessor;
import br.com.isaquebrb.iftm.batchcreditanalysis.reader.CustomReader;
import br.com.isaquebrb.iftm.batchcreditanalysis.writer.CustomWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobStep {

    private final StepBuilderFactory stepBuilder;
    private final CustomReader customReader;
    private final CustomProcessor customProcessor;
    private final CustomWriter customWriter;
    private final ProcessorListener listener;

    public Step processFileStep(String pathFile) {
        return stepBuilder.get("processFileStep")
                .<CreditAnalysis, CreditAnalysis>chunk(100)
                .reader(customReader.documentReader(pathFile))
                .processor(customProcessor.documentProcessor())
                .writer(customWriter.documentWriter())
                .listener(listener)
                .faultTolerant()
                .skipPolicy(new CustomSkipPolicy())
                .build();
    }
}
