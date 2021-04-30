package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreditRecovery extends CommonInfo {

    public CreditRecovery(String hasInfo, CreditRecoveryContent content) {
        super(hasInfo);
        this.content = content;
    }

    @JsonProperty("conteudo")
    private CreditRecoveryContent content;
}
