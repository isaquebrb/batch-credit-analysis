package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FinancialPendency {

    @JsonProperty("existe_informacao")
    private String hasInfo;

    @JsonProperty("quantidade")
    private Integer quantity;

    @JsonProperty("conteudo")
    private FinancialPendencyContent content;
}
