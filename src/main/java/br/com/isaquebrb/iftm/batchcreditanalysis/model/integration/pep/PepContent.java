package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.pep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
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
