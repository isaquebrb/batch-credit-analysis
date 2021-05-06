package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Crednet {

    @JsonProperty("confirmei")
    protected CrednetPerson personInfo;

    @JsonProperty("pendencias_financeiras")
    protected FinancialPendency financialPendency;

    @JsonProperty("bacen")
    protected Bacen bacen;

    @JsonProperty("protesto_estadual")
    protected StateProtest stateProtest;

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

    @JsonProperty("faturamento_presumido")
    private PresumedBilling presumedBilling;

    @JsonProperty("classificacao_risco_credito")
    private CreditRiskRating creditRiskRating;
}
