package br.com.isaquebrb.iftm.batchcreditanalysis.controller;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatus;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonType;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
public class JobController {

    private final JobLauncher jobLauncher;
    private final CreditAnalysisService service;
    private final Job processFileJobSample;

    @GetMapping("/start")
    public ResponseEntity<Void> startJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = new JobParametersBuilder().addLong("uniqueJob", System.nanoTime()).toJobParameters();
        JobExecution execution = jobLauncher.run(processFileJobSample, parameters);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> testAsync() {
        return ResponseEntity.ok("Testado");
    }

    @GetMapping("/save")
    public ResponseEntity<CreditAnalysis> saveAnalysis() {
        CreditAnalysis analysis = new CreditAnalysis();
        analysis.setDocument("123456789");
        analysis.setPersonType(PersonType.PJ);
        analysis.setStatus(AnalysisStatus.ERROR);
        analysis.setStartDate(LocalDateTime.now().minusMinutes(2));
        analysis.setEndDate(LocalDateTime.now());
        return ResponseEntity.ok(service.save(analysis));
    }
}
