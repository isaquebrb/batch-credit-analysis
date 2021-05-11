package br.com.isaquebrb.iftm.batchcreditanalysis.service;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.DatabaseException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.parameter.BooleanParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.parameter.IntegerParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.parameter.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.parameter.StringParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.ParameterRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.ParameterResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.Parameter;
import br.com.isaquebrb.iftm.batchcreditanalysis.repository.ParameterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterService {

    private final ParameterRepository repository;

    public ParameterResponse save(ParameterRequest request) {
        try {
            Parameter newParameter = repository.save(request.toEntity());
            return newParameter.toDto();
        } catch (DataIntegrityViolationException e) {
            log.error("[ParameterService.save] Erro ao salvar o novo parametro, o nome {} ja existe",
                    request.getName());
            throw new DatabaseException("Erro ao salvar o novo parametro, o nome " + request.getName() + " ja existe");
        } catch (Exception e) {
            log.error("[ParameterService.save] Erro ao salvar o novo parametro {}", request.toString(), e);
            throw new DatabaseException("Erro ao salvar o novo parametro " + request.toString());
        }
    }

    public List<ParameterResponse> findAll() {
        List<Parameter> parameters = repository.findAll();
        return parameters.stream().map(Parameter::toDto).collect(Collectors.toList());
    }

    public ParameterResponse findById(Long id) {
        return getParameterById(id).toDto();
    }

    public ParameterResponse update(Long id, ParameterRequest request) {
        Parameter parameter = getParameterById(id);
        parameter.setName(request.getName());
        parameter.setDescription(request.getDescription());
        parameter.setStringValue(request.getStringValue());
        parameter.setIntegerValue(request.getIntegerValue());
        parameter.setNumericValue(request.getNumericValue());
        parameter.setBooleanValue(request.getBooleanValue());
        parameter.setActive(request.getActive());

        try {
            return repository.save(parameter).toDto();
        } catch (Exception e) {
            log.error("[ParameterService.update] Erro ao atualizar o parametro id {} com {}",
                    id, request.toString(), e);
            throw new DatabaseException("Erro ao atualizar o parametro id " + id +
                    " com " + request.toString());
        }
    }

    private Parameter getParameterById(Long id) {
        try {
            return repository.findById(id).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException e) {
            log.error("[ParameterService.getParameterById] Erro ao buscar o parametro de id {}", id);
            throw new DatabaseException("Erro ao buscar o parametro de id " + id);
        } catch (IllegalArgumentException e) {
            log.error("[ParameterService.getParameterById] O id esta vazio");
            throw new SystemException("O id esta vazio");
        }
    }

    public Integer getParameter(IntegerParameterEnum parameter) {
        return repository.findByName(parameter.name()).map(Parameter::getIntegerValue)
                .orElse(parameter.getDefaultValue());
    }

    public Boolean getParameter(BooleanParameterEnum parameter) {
        return repository.findByName(parameter.name()).map(Parameter::getBooleanValue)
                .orElse(parameter.getDefaultValue());
    }

    public BigDecimal getParameter(NumericParameterEnum parameter) {
        return repository.findByName(parameter.name()).map(Parameter::getNumericValue)
                .orElse(parameter.getDefaultValue());
    }

    public String getParameter(StringParameterEnum parameter) {
        return repository.findByName(parameter.name()).map(Parameter::getStringValue)
                .orElse(parameter.getDefaultValue());
    }
}
