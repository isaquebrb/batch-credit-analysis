package br.com.isaquebrb.iftm.batchcreditanalysis.model.request;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@ToString
public class ParameterRequest {

    private String name;

    private String description;

    private String stringValue;

    private Integer integerValue;

    private BigDecimal numericValue;

    private Boolean booleanValue;

    @NotNull(message = "Active is mandatory")
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
