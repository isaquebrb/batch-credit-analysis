package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class CommonInfo {

    @JsonProperty("existe_informacao")
    private String hasInformation;

}
