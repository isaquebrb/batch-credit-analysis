package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreditRecoveryContent {

    @JsonProperty("faixa")
    private String range;

    @JsonProperty("mensagem")
    private String message;
}
