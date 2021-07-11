package br.com.isaquebrb.iftm.batchcreditanalysis.controller;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.CreditAnalysisDTO;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
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
    public ResponseEntity<Page<CreditAnalysisDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("job-execution/{jobExecutionId}")
    public ResponseEntity<Page<CreditAnalysisDTO>> findAllByJobExecutionId(@PathVariable Long jobExecutionId,
                                                                           Pageable pageable) {
        return ResponseEntity.ok(service.findAllByJobExecutionId(jobExecutionId, pageable));
    }

    @GetMapping("job-execution/{jobExecutionId}/status")
    public ResponseEntity<Page<CreditAnalysisDTO>> findAllByJobExecutionIdAndStatus(@PathVariable Long jobExecutionId,
                                                                                    @RequestParam AnalysisStatusEnum status,
                                                                                    Pageable pageable) {
        return ResponseEntity.ok(service.findAllByJobExecutionIdAndStatus(jobExecutionId, status, pageable));
    }

    @GetMapping("document/{document}")
    public ResponseEntity<List<CreditAnalysisDTO>> findAllByDocument(@PathVariable String document) {
        return ResponseEntity.ok(service.findAllByDocument(document));
    }
}
