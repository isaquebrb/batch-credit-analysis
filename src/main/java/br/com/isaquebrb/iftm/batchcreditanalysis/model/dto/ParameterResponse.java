package br.com.isaquebrb.iftm.batchcreditanalysis.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ParameterResponse {

    private Long id;
    private String name;
    private String description;
    private String stringValue;
    private Integer integerValue;
    private BigDecimal numericValue;
    private Boolean booleanValue;
    private Boolean active;
}
