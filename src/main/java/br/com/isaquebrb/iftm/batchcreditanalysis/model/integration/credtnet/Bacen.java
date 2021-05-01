package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Bacen extends CommonInfo {

    @JsonProperty("quantidade")
    private Integer quantity;

    @JsonProperty("conteudo")
    private BacenContent content;
}
