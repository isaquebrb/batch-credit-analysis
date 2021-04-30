package br.com.isaquebrb.iftm.batchcreditanalysis.service;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.DatabaseException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.repository.CreditAnalysisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
            log.error("[CreditAnalysisService.save] Error trying to save {}", analysis.toString(), e);
            throw new DatabaseException(e.getMessage());
        }
    }
}
