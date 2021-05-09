package br.com.isaquebrb.iftm.batchcreditanalysis.model;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.EnumMap;

@Getter
@Setter
@NoArgsConstructor
public class ProcessingHistory implements Serializable {

    private EnumMap<AnalysisValidationEnum, AnalysisStatusEnum> processorsStatus = new EnumMap<>(AnalysisValidationEnum.class);

    private Integer personAge;

    private BigDecimal valueBacenChecks;

    private String documentSituation;

    private BigDecimal valueFinancialPendencies;

    private BigDecimal noPaymentRate;

    private Boolean isPep;

    private Integer scoreSerasa;

    private Boolean isDead;

    private BigDecimal valueStateProtests;
}
