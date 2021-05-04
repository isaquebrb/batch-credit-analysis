package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

import lombok.Getter;

@Getter
public enum BooleanParameterEnum implements IParameter<Boolean> {

    ;

    BooleanParameterEnum(String description, Boolean defaultValue) {
        this.description = description;
        this.defaultValue = defaultValue;
    }

    private String description;
    private Boolean defaultValue;

    @Override
    public Boolean getDefaultValue() {
        return this.defaultValue;
    }
}