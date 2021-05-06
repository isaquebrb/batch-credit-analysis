package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NameContent {

    @JsonProperty("documento")
    private String document;

    @JsonProperty("tipo_documento")
    private String documentType;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("data_nascimento")
    private String birthdayDate;

    @JsonProperty("idade")
    private String age;

    @JsonProperty("sexo")
    private String gender;

    @JsonProperty("estrangeiro")
    private Foreign foreign;

    @JsonProperty("nome_fantasia")
    private String tradeName;

    @JsonProperty("cnae_primaria")
    private String primaryCnae;

    @JsonProperty("descricao_cnae_primaria")
    private String descriptionPrimaryCnae;
}
