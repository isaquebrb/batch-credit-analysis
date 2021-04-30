package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Death extends CommonInfo {

    public Death(String hasInfo, DeathContent content) {
        super(hasInfo);
        this.content = content;
    }

    @JsonProperty("conteudo")
    private DeathContent content;
}
