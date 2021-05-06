package br.com.isaquebrb.iftm.batchcreditanalysis.service;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.DatabaseException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ParameterReq;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ParameterResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.Parameter;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.*;
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

    public ParameterResponse save(ParameterReq request) {
        try {
            Parameter newParameter = repository.save(request.toEntity());
            return newParameter.toDto();
        } catch (DataIntegrityViolationException e) {
            log.error("[ParameterService.save] The name {} already exist in parameter table", request.getName(), e);
            throw new DatabaseException("The name " + request.getName() + " already exist in parameter table");
        } catch (Exception e) {
            log.error("[ParameterService.save] Error trying to save new parameter {}", request.toString(), e);
            throw new DatabaseException("Error trying to save new parameter " + request.toString());
        }
    }

    public List<ParameterResponse> findAll() {
        List<Parameter> parameters = repository.findAll();
        return parameters.stream().map(Parameter::toDto).collect(Collectors.toList());
    }

    public ParameterResponse findById(Long id) {
        try {
            return getParameterById(id).toDto();
        } catch (EntityNotFoundException e) {
            log.error("[ParameterService.findById] Error trying to find parameter id {}", id);
            throw new DatabaseException("Error trying to find parameter id " + id);
        } catch (Exception e) {
            log.error("[ParameterService.findById] Error trying to find parameter id {}", id, e);
            throw new DatabaseException(e.getMessage());
        }
    }

    public ParameterResponse update(Long id, ParameterReq request) {
        Parameter parameter = getParameterById(id);
        parameter.setDescription(request.getDescription());
        parameter.setStringValue(request.getStringValue());
        parameter.setIntegerValue(request.getIntegerValue());
        parameter.setNumericValue(request.getNumericValue());
        parameter.setBooleanValue(request.getBooleanValue());

        try {
            return repository.save(parameter).toDto();
        } catch (Exception e) {
            log.error("[ParameterService.update] Error trying to update parameter {}", request.toString(), e);
            throw new DatabaseException("Error trying to update parameter " + request.toString());
        }
    }

    public Parameter findByName(String name) {
        try {
            return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException e) {
            //todo test non existing name
            log.error("[ParameterService.findByName] Error trying get parameter with name {}", name);
            throw new SystemException("");
        }
    }

    private Parameter getParameterById(Long id) {
        try {
            return repository.findById(id).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException e) {
            log.error("[ParameterService.getParameterById] Error trying to find parameter id {}", id);
            throw new DatabaseException("Error trying to find parameter id " + id);
        } catch (IllegalArgumentException e) {
            log.error("[ParameterService.getParameterById] Id is null");
            throw new SystemException("Error trying to get parameter with id null");
        }
    }

    //todo cache
    public Integer getParameter(IntegerParameterEnum parameter) {
        return repository.findByName(parameter.name()).map(Parameter::getIntegerValue)
                .orElse(parameter.getDefaultValue());
    }

    public Boolean getParameter(BooleanParameterEnum parameter) {
        return repository.findByName(parameter.name()).map(Parameter::getBooleanValue)
                .orElse(parameter.getDefaultValue());
    }

    public BigDecimal getParameter(NumericParameterEnum parameter){
        return repository.findByName(parameter.name()).map(Parameter::getNumericValue)
                .orElse(parameter.getDefaultValue());
    }

    public String getParameter(StringParameterEnum parameter) {
        return repository.findByName(parameter.name()).map(Parameter::getStringValue)
                .orElse(parameter.getDefaultValue());
    }
}
