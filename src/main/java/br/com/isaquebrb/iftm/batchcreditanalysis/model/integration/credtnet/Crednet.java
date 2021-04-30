package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Crednet {

    @JsonProperty("confirmei")
    protected CrednetPerson personInfo;

    @JsonProperty("pendencias_financeiras")
    protected FinancialPendency financialPendency;

    @JsonProperty("bacen")
    protected Bacen bacen;

    @JsonProperty("protesto_estadual")
    protected StateProtest stateProtest;
}
