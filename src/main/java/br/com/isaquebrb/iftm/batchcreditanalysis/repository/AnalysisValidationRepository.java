package br.com.isaquebrb.iftm.batchcreditanalysis.repository;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.AnalysisValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisValidationRepository extends JpaRepository<AnalysisValidation, Long> {

    @Query(value = "SELECT av FROM AnalysisValidation av WHERE av.active = :isActive")
    List<AnalysisValidation> findAllByActive(Boolean isActive);
}
