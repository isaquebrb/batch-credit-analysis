package br.com.isaquebrb.iftm.batchcreditanalysis;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients
@EnableBatchProcessing
@SpringBootApplication
@EnableAsync
@EnableCaching
public class BatchCreditAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchCreditAnalysisApplication.class, args);
    }

}
