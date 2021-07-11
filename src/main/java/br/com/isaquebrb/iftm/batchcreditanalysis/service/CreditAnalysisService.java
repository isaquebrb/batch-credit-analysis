package br.com.isaquebrb.iftm.batchcreditanalysis.service;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.DatabaseException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.repository.CreditAnalysisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditAnalysisService {

    private final CreditAnalysisRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CreditAnalysis save(CreditAnalysis analysis) {
        try {
            return repository.save(analysis);
        } catch (Exception e) {
            log.error("[CreditAnalysisService.save] Erro tentando salvar a analise {}.", analysis.toString(), e);
            throw new DatabaseException(e.getMessage());
        }
    }

    public Page<CreditAnalysis> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<CreditAnalysis> findAllByJobExecutionId(Long jobExecutionId, Pageable pageable) {
        return repository.findAllByJobExecutionId(jobExecutionId, pageable);
    }

    public List<CreditAnalysis> findAllByDocument(String document) {
        return repository.findAllByDocument(document);
    }
}
