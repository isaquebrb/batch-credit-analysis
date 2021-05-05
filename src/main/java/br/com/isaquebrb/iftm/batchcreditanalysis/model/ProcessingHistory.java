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
    private Integer amountBacenChecks;
    private AnalysisStatusEnum bacenAnalysis;
}
