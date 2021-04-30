package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FinancialPendencyContent {

    @JsonProperty("detalhes")
    public FinancialPendencyDetail pendenciesDetails;
}
