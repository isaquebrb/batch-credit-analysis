package br.com.isaquebrb.iftm.batchcreditanalysis.service;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobLauncher jobLauncher;
    private final JobLocator jobLocator;
    private final JobOperator jobOperator;

    @Async
    public void runJob(String jobName, JobParameters parameters) {
        try {
            log.info("[JobService.runJob] Iniciando o job {}", jobName);
            Job job = jobLocator.getJob(jobName);
            jobLauncher.run(job, parameters);
        } catch (Exception e) {
            throw handleException(e, jobName);
        }
    }

    public void stopJob(String jobName) {
        try {
            Set<Long> jobs = jobOperator.getRunningExecutions(jobName);
            jobOperator.stop(jobs.iterator().next());
            log.info("[JobService.stopJob] Job {} encerrado por solicitacao ", jobName);
        } catch (Exception e) {
            throw handleException(e, jobName);
        }
    }

    private SystemException handleException(Exception e, String jobName) {
        String message;

        if (e instanceof NoSuchJobException) {
            message = "Erro ao tentar localizar o job " + jobName;
        } else if (e instanceof JobRestartException) {
            //todo test restart
            message = "Erro ao reiniciar o job " + jobName;
        } else if (e instanceof JobExecutionAlreadyRunningException) {
            message = "O job " + jobName + " ja esta em execucao";
        } else if (e instanceof JobInstanceAlreadyCompleteException) {
            message = "O job " + jobName + " ja foi executado";
        } else if (e instanceof JobExecutionNotRunningException) {
            message = "O job " + jobName + " nao esta sendo executado no momento";
        } else if (e instanceof NoSuchJobExecutionException) {
            message = "Erro ao tentar finalizar o job " + jobName;
        } else if (e instanceof JobParametersInvalidException) {
            message = "Parametros do job " + jobName + " invalidos";
        } else {
            message = "Erro desconhecido. Job: " + jobName;
        }

        log.error("[JobService.handleException]" + message);
        throw new SystemException(message);
    }
}
