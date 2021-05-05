package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Email extends CommonInfo {

    @JsonProperty("conteudo")
    private EmailContent content;
}
