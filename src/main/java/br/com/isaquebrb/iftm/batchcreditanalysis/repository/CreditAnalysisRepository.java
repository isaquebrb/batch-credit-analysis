package br.com.isaquebrb.iftm.batchcreditanalysis.repository;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditAnalysisRepository extends JpaRepository<CreditAnalysis, Long> {
}
