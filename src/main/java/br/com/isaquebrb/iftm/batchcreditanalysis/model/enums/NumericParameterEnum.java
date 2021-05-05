package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

import java.math.BigDecimal;

public enum NumericParameterEnum implements IParameter<BigDecimal> {

    //todo save in database
    MAX_VALUE_BACEN_CHECKS("Valor máximo de cheques sem fundo", BigDecimal.valueOf(500)),
    MAX_VALUE_FINANCIAL_PENDENCY("Valor máximo de pedências financeiras", BigDecimal.valueOf(500));

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
