package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StateProtest {

    @JsonProperty("existe_informacao")
    private String hasInfo;

    @JsonProperty("conteudo")
    private StateProtestContent content;

    @JsonProperty("quantidade")
    private Integer quantity;
}
