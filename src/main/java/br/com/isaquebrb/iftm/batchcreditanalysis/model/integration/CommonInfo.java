package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class CommonInfo {

    @JsonProperty("existe_informacao")
    private String hasInformation;

}
