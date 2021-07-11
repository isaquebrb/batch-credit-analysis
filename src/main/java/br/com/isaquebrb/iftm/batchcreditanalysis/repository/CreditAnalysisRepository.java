package br.com.isaquebrb.iftm.batchcreditanalysis.repository;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditAnalysisRepository extends JpaRepository<CreditAnalysis, Long> {

    Page<CreditAnalysis> findAllByJobExecutionId(Long jobExecutionId, Pageable pageable);

    List<CreditAnalysis> findAllByDocument(String document);
}
