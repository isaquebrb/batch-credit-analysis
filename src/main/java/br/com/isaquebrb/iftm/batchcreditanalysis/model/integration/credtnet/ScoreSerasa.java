package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ScoreSerasa extends CommonInfo {

    public ScoreSerasa(String hasInfo, ScoreSerasaContent content) {
        super(hasInfo);
        this.content = content;
    }

    @JsonProperty("conteudo")
    private ScoreSerasaContent content;
}
