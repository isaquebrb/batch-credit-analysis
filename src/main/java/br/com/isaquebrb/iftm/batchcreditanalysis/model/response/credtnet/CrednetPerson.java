package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CrednetPerson extends CommonInfo {

    @JsonProperty("conteudo")
    private CrednetPersonContent content;
}