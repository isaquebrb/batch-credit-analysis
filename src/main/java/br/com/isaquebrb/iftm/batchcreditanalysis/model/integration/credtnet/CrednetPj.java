package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CrednetPj extends Crednet {

    @JsonProperty("faturamento_presumido")
    private PresumedBilling presumedBilling;

    @JsonProperty("classificacao_risco_credito")
    private CreditRiskRating creditRiskRating;
}
