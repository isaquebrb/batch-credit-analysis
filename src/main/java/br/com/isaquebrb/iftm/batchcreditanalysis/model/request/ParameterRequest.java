package br.com.isaquebrb.iftm.batchcreditanalysis.model.request;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@ToString
public class ParameterRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private String stringValue;

    private Integer integerValue;

    private BigDecimal numericValue;

    private Boolean booleanValue; //todo test update/save with string in this field

    //todo @NotNull(message = "Active is mandatory") test not sending this
    private Boolean active;

    public Parameter toEntity() {
        Parameter parameter = new Parameter();
        parameter.setName(this.name);
        parameter.setDescription(this.description);
        parameter.setStringValue(this.stringValue);
        parameter.setIntegerValue(this.integerValue);
        parameter.setNumericValue(this.numericValue);
        parameter.setBooleanValue(this.booleanValue);
        parameter.setActive(this.active);
        return parameter;
    }
}
