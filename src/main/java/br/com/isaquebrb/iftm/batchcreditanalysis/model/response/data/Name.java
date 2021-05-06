package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Name extends CommonInfo {

    @JsonProperty("conteudo")
    private NameContent content;
}
