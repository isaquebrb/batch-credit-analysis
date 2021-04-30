package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CrednetResponseContent {

    @JsonProperty("confirmei")
    private Crednet crednet;

    @JsonProperty("pendencias_financeiras")
    private FinancialPendency financialPendency;

    @JsonProperty("bacen")
    private Bacen bacen;

    @JsonProperty("protesto_estadual")
    private StateProtest stateProtest;

    @JsonProperty("faturamento_presumido")
    private PresumedBilling presumedBilling;

    @JsonProperty("classificacao_risco_credito")
    private CreditRiskRating creditRiskRating;

    @JsonProperty("obito")
    private Death deathInfo;

    @JsonProperty("score_serasa")
    private ScoreSerasa scoreSerasaInfo;

    @JsonProperty("renda_pro")
    @JsonAlias("renda_presumida")
    private PresumedIncome presumedIncomeInfo;

    @JsonProperty("capacidade_mensal_pagamento")
    private MonthlyPaymentCapacity monthlyPaymentCapacityInfo;

    @JsonProperty("recuperacao_credito")
    private CreditRecovery creditRecovery;
}
