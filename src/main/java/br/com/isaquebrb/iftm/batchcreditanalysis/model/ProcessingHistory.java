package br.com.isaquebrb.iftm.batchcreditanalysis.model;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProcessingHistory {

    private Integer personAge;
    private AnalysisStatusEnum ageAnalysis;

    private BigDecimal valueBacenChecks;
    private AnalysisStatusEnum bacenPendencyAnalysis;

    private String documentSituation;
    private AnalysisStatusEnum documentSituationAnalysis;

    private BigDecimal valueFinancialPendencies;
    private AnalysisStatusEnum financialPendencyAnalysis;

    private BigDecimal noPaymentRate;
    private AnalysisStatusEnum noPaymentAnalysis;

    private Boolean isPep;
    private AnalysisStatusEnum pepAnalysis;

    private Integer scoreSerasa;
    private AnalysisStatusEnum scoreSerasaAnalysis;

    private Boolean isDead;
    private AnalysisStatusEnum deathAnalysis;

    private BigDecimal valueStateProtests;
    private AnalysisStatusEnum stateProtestAnalysis;
}
