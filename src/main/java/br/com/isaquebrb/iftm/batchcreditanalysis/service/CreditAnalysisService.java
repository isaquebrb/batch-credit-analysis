package br.com.isaquebrb.iftm.batchcreditanalysis.service;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.DatabaseException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.CreditAnalysisDTO;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.repository.CreditAnalysisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditAnalysisService {

    private final CreditAnalysisRepository repository;

    public CreditAnalysis save(CreditAnalysis analysis) {
        try {
            return repository.save(analysis);
        } catch (Exception e) {
            log.error("[CreditAnalysisService.save] Erro tentando salvar a analise {}.", analysis.toString(), e);
            throw new DatabaseException(e.getMessage());
        }
    }

    public Page<CreditAnalysisDTO> findAll(Pageable pageable) {
        Page<CreditAnalysis> creditAnalysisPage = repository.findAll(pageable);
        return creditAnalysisPage.map(CreditAnalysis::toDto);
    }

    public Page<CreditAnalysisDTO> findAllByJobExecutionId(Long jobExecutionId, Pageable pageable) {
        Page<CreditAnalysis> creditAnalysisPage = repository.findAllByJobExecutionId(jobExecutionId, pageable);
        return creditAnalysisPage.map(CreditAnalysis::toDto);
    }

    public Page<CreditAnalysisDTO> findAllByJobExecutionIdAndStatus(Long jobExecutionId, AnalysisStatusEnum status, Pageable pageable) {
        Page<CreditAnalysis> creditAnalysisPage = repository.findAllByJobExecutionIdAndStatus(jobExecutionId, status, pageable);
        return creditAnalysisPage.map(CreditAnalysis::toDto);
    }

    public List<CreditAnalysisDTO> findAllByDocument(String document) {
        List<CreditAnalysis> creditAnalysisList = repository.findAllByDocument(document);
        return creditAnalysisList.stream().map(CreditAnalysis::toDto).collect(Collectors.toList());
    }
}
