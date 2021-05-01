package br.com.isaquebrb.iftm.batchcreditanalysis.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ParameterResponse {

    private Long id;

    private String name;

    private String description;

    private String stringValue;

    private Integer integerValue;

    private Double numericValue;

    private Boolean booleanValue;
}
