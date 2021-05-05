package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OriginCountry {

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("origem")
    private String origin;
}
