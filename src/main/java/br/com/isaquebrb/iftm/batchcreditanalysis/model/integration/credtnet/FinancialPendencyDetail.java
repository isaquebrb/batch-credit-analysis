package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class FinancialPendencyDetail {

    @JsonProperty("total_ocorrencia")
    private Integer totalOccurrences;

    @JsonProperty("data_ocorrencia_mais_antiga")
    private String lastOccurrenceDate;

    @JsonProperty("data_ocorrencia_mais_recente")
    private String recentOccurrenceDate;

    @JsonProperty("valor")
    private String value;

    @JsonProperty("tipo_anotacao")
    private String typeAnnotation;

    @JsonProperty("pendencias")
    private List<PendencyDetail> pendencyDetailList;
}
