package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

public enum StringParameterEnum implements IParameter<String> {

    RF_DOC_SITUATION("Situação do documento na receita federal", "REGULAR");

    StringParameterEnum(String description, String defaultValue){
        this.description = description;
        this.defaultValue = defaultValue;
    }

    private String description;
    private String defaultValue;

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }
}
