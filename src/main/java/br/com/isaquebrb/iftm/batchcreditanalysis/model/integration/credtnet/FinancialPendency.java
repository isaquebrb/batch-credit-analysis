package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FinancialPendency extends CommonInfo {

    public FinancialPendency(String hasInfo, FinancialPendencyContent content, Integer quantity) {
        super(hasInfo);
        this.content = content;
        this.quantity = quantity;
    }

    @JsonProperty("quantidade")
    private Integer quantity;

    @JsonProperty("conteudo")
    private FinancialPendencyContent content;
}
