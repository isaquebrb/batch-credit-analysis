package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PresumedBilling extends CommonInfo {

    public PresumedBilling(String hasInfo, PresumedBillingContent content) {
        super(hasInfo);
        this.content = content;
    }

    @JsonProperty("conteudo")
    private PresumedBillingContent content;
}
