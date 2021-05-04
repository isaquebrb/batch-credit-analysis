package br.com.isaquebrb.iftm.batchcreditanalysis.repository;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Parameter findByName(String name);
}
