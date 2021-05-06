package br.com.isaquebrb.iftm.batchcreditanalysis.model.response;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.pep.Pep;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PepResponse {

    @JsonProperty("content")
    private Pep pep;

    private String date;

    private String hour;
}
