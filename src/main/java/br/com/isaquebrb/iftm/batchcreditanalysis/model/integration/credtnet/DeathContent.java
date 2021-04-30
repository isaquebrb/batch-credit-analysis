package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DeathContent {

    @JsonProperty("mensagem")
    private String message;
}
