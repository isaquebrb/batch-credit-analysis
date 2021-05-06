package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ScoreSerasaContent {

    @JsonProperty("score")
    private String score;

    @JsonProperty("percentual")
    private String percentage;

    @JsonProperty("mensagem")
    private String message;
}
