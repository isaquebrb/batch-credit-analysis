package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MonthlyPaymentCapacityContent {

    @JsonProperty("valor")
    private String value;
}
