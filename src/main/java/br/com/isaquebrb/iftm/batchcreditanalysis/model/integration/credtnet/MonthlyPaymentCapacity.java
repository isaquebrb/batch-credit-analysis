package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MonthlyPaymentCapacity extends CommonInfo {

    public MonthlyPaymentCapacity(String hasInfo, MonthlyPaymentCapacityContent content) {
        super(hasInfo);
        this.content = content;
    }

    @JsonProperty("conteudo")
    private MonthlyPaymentCapacityContent content;
}
