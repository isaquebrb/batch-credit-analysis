package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PendencyDetail {

    @JsonProperty("data_ocorrencia")
    private String date;

    @JsonProperty("modalidade")
    private String modality;

    @JsonProperty("avalista")
    private String guarantor;

    @JsonProperty("valor")
    private String value;

    @JsonProperty("contrato")
    private String contract;

    @JsonProperty("origem")
    private String origin;

    @JsonProperty("tipo_anotacao")
    private String typeAnnotation;
}
