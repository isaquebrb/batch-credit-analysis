package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StateProtest extends CommonInfo {

    @JsonProperty("conteudo")
    private StateProtestContent content;

    @JsonProperty("quantidade")
    private Integer quantity;

}
