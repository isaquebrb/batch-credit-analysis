package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CrednetPf extends Crednet {

    @JsonProperty("obito")
    private Death death;

    @JsonProperty("score_serasa")
    private ScoreSerasa scoreSerasa;

    @JsonProperty("renda_pro")
    private PresumedIncome presumedIncome;

    @JsonProperty("capacidade_mensal_pagamento")
    private MonthlyPaymentCapacity monthlyPaymentCapacity;

    @JsonProperty("recuperacao_credito")
    private CreditRecovery creditRecovery;
}
