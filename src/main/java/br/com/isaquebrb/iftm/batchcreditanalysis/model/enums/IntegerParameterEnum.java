package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

public enum IntegerParameterEnum implements IParameter<Integer> {

    MIN_AGE("Idade Máxima", 18),
    MAX_AGE("Idade Mínima", 60);

    IntegerParameterEnum(String description, Integer defaultValue) {
        this.description = description;
        this.defaultValue = defaultValue;
    }

    private String description;
    private Integer defaultValue;

    @Override
    public Integer getDefaultValue() {
        return defaultValue;
    }
}
