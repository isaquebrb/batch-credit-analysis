package br.com.isaquebrb.iftm.batchcreditanalysis.model.dto;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@ToString
public class ParameterRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private String stringValue;

    private Integer integerValue;

    private Double numericValue;

    private Boolean booleanValue;

    public Parameter toEntity() {
        Parameter parameter = new Parameter();
        parameter.setName(this.name);
        parameter.setDescription(this.description);
        parameter.setStringValue(this.stringValue);
        parameter.setIntegerValue(this.integerValue);
        parameter.setNumericValue(this.numericValue);
        parameter.setBooleanValue(this.booleanValue);
        return parameter;
    }
}
