package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BacenContent {

    @JsonProperty("data_ocorrencia_mais_antiga")
    private String lastOccurrenceDate;

    @JsonProperty("data_ocorrencia_mais_recente")
    private String recentOccurrenceDate;

    @JsonProperty("cheques_bacen")
    private List<BacenChecks> bacenChecks;
}
