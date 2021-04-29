package br.com.isaquebrb.iftm.batchcreditanalysis.config;

import br.com.isaquebrb.iftm.batchcreditanalysis.step.JobStep;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilder;
    private final JobStep step;

    @Bean
    public Job processFileJob() {
        return jobBuilder.get("processFileJob")
                .start(step.processFileStep())
                .build();
    }

    /* Stop a service
    Set<Long> executions = jobOperator.getRunningExecutions("sampleJob");
    jobOperator.stop(executions.iterator().next());
    */
}
