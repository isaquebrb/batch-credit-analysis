package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CreditRiskRatingContent {

    @JsonProperty("pontuacao")
    private String scorePoints;

    @JsonProperty("inadimplencia")
    private String defaultRate;

    @JsonProperty("mensagem")
    private String message;
}
