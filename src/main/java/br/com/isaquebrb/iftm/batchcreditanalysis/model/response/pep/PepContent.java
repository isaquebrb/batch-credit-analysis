package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.pep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PepContent {

    @JsonProperty("funcao")
    private String occupation;

    @JsonProperty("orgao")
    private String agency;

    @JsonProperty("inicio_exercicio")
    private String exerciseStartDate;

    @JsonProperty("fim_exercicio")
    private String exerciseEndDate;

    @JsonProperty("fim_carencia")
    private String shortageEndDate;
}
