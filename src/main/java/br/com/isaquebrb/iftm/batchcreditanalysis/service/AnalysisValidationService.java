package br.com.isaquebrb.iftm.batchcreditanalysis.service;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.AnalysisValidation;
import br.com.isaquebrb.iftm.batchcreditanalysis.repository.AnalysisValidationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisValidationService {

    private final AnalysisValidationRepository repository;

    //todo cache
    public List<AnalysisValidation> findAllByActive(Boolean isActive) {
        return repository.findAllByActive(isActive);
    }
}
