package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CrednetPerson extends CommonInfo {

    public CrednetPerson(String hasInfo, CrednetPersonContent content) {
        super(hasInfo);
        this.content = content;
    }

    @JsonProperty("conteudo")
    private CrednetPersonContent content;
}
