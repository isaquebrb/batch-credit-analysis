package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

import java.math.BigDecimal;

public enum NumericParameterEnum implements IParameter<BigDecimal> {

    MAX_VALUE_BACEN_CHECKS("Valor máximo de cheques sem fundo", BigDecimal.valueOf(1000));

    NumericParameterEnum(String description, BigDecimal defaultValue) {
        this.description = description;
        this.defaultValue = defaultValue;
    }

    private String description;
    private BigDecimal defaultValue;

    @Override
    public BigDecimal getDefaultValue() {
        return defaultValue;
    }
}
