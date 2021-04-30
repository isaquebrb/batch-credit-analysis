package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StateProtest extends CommonInfo {

    public StateProtest(String hasInfo, StateProtestContent content, Integer quantity) {
        super(hasInfo);
        this.content = content;
        this.quantity = quantity;
    }

    @JsonProperty("conteudo")
    private StateProtestContent content;

    @JsonProperty("quantidade")
    private Integer quantity;

}
