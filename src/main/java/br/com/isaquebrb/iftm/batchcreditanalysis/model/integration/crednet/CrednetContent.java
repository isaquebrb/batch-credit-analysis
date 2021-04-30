package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CrednetContent {

    @JsonProperty("nome_razao")
    private String companyName;

    @JsonProperty("data_nascimento_fundacao")
    private String birthdateInstitution;

    @JsonProperty("situacao_documento")
    private String documentSituation;

    @JsonProperty("data_situacao_documento")
    private String documentSituationDate;
}
