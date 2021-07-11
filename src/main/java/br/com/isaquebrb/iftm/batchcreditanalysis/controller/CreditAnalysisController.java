package br.com.isaquebrb.iftm.batchcreditanalysis.controller;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("credit-analysis")
@RequiredArgsConstructor
public class CreditAnalysisController {

    private final CreditAnalysisService service;

    @GetMapping
    public ResponseEntity<Page<CreditAnalysis>> findAll(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("job-execution/{jobExecutionId}")
    public ResponseEntity<Page<CreditAnalysis>> findAllByJobExecutionId(@PathVariable Long jobExecutionId, Pageable pageable){
        return ResponseEntity.ok(service.findAllByJobExecutionId(jobExecutionId, pageable));
    }

    @GetMapping("document/{document}")
    public ResponseEntity<List<CreditAnalysis>> findAllByDocument(@PathVariable String document){
        return ResponseEntity.ok(service.findAllByDocument(document));
    }
}
