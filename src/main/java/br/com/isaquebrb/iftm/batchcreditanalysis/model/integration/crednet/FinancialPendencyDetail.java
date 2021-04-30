package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FinancialPendencyDetail {

    @JsonProperty("total_ocorrencia")
    private Integer totalOccurrence;

    @JsonProperty("data_ocorrencia_mais_antiga")
    private String oldestOccurrenceDate;

    @JsonProperty("data_ocorrencia_mais_recente")
    private String newestOccurrenceDate;

    @JsonProperty("valor")
    private String value;

    @JsonProperty("tipo_anotacao")
    private String typeAnnotation;

    @JsonProperty("pendencias")
    private List<PendencyDetail> pendencyDetails = new ArrayList<>();
}
