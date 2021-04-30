package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StateProtestDetail {

    @JsonProperty("data_ocorrencia")
    private String date;

    @JsonProperty("valor")
    private String value;

    @JsonProperty("cartorio")
    private String registryOffice;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("uf")
    private String state;

    @JsonProperty("tipo_anotacao")
    private String typeAnnotion;
}
