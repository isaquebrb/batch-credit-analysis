package br.com.isaquebrb.iftm.batchcreditanalysis.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Jobs {

    private final JobBuilderFactory jobBuilder;
    private final Steps step;
    private final JobRegistry jobRegistry;

    @Value("${file.input}")
    private String pathFile;

    @Value("${file.input.sample}")
    private String pathFileSample;

    @Bean
    public Job processFileJob() {
        return jobBuilder.get("processFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step.processFileStep(pathFile))
                .build();
    }

    @Bean
    public Job processFileJobSample() {
        return jobBuilder.get("processFileJobSample")
                .incrementer(new RunIdIncrementer())
                .start(step.processFileStep(pathFileSample))
                .build();
    }

    /**
     *  Register jobs in context to be used in a job locator/operator
     */
    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        return postProcessor;
    }
}
