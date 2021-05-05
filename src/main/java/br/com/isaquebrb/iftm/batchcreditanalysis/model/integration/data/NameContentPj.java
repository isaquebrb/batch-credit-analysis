package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class NameContentPj extends NameContent {

    @JsonProperty("nome_fantasia")
    private String tradeName;

    @JsonProperty("cnae_primaria")
    private String primaryCnae;

    @JsonProperty("descricao_cnae_primaria")
    private String descriptionPrimaryCnae;
}
