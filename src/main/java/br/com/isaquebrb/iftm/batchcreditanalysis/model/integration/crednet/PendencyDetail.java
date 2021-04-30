package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PendencyDetail {

    @JsonProperty("data_ocorrencia")
    private String occurrenceDate;

    @JsonProperty("modalidade")
    private String modality;

    @JsonProperty("avalista")
    private String avalista;

    @JsonProperty("valor")
    private String valor;

    @JsonProperty("contrato")
    private String contract;

    @JsonProperty("origem")
    private String origin;

    @JsonProperty("tipo_anotacao")
    private String typeAnnotation;

}
