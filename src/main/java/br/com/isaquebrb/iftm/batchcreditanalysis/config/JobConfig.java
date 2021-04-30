package br.com.isaquebrb.iftm.batchcreditanalysis.config;

import br.com.isaquebrb.iftm.batchcreditanalysis.step.JobStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilder;
    private final JobStep step;

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

    /* Stop a service
    Set<Long> executions = jobOperator.getRunningExecutions("sampleJob");
    jobOperator.stop(executions.iterator().next());
    */
}
