package br.com.isaquebrb.iftm.batchcreditanalysis.controller;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.JobRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    @PostMapping("/run")
    public ResponseEntity<Void> startJob(@RequestBody @Valid JobRequest request) {
        if (request.getParameters() == null || request.getParameters().isEmpty())
            request.setParameters(getDefaultParams());

        jobService.runJob(request.getJobName(), request.getParameters());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stop")
    public ResponseEntity<Void> stopJob(@RequestBody @Valid JobRequest request) {
        jobService.stopJob(request.getJobName());
        return ResponseEntity.ok().build();
    }

    private JobParameters getDefaultParams() {
        return new JobParametersBuilder().addLong("uniqueJob", System.nanoTime()).toJobParameters();
    }
}