package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreditRiskRatingContent {

    @JsonProperty("pontuacao")
    private String scorePoints;

    @JsonProperty("inadimplencia")
    private String nonPayment;

    @JsonProperty("mensagem")
    private String message;
}
