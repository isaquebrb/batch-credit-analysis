package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Foreign extends CommonInfo {

    @JsonProperty("estrangeiro")
    private String isForeign;

    @JsonProperty("pais_origem")
    private OriginCountry originCountry;
}
