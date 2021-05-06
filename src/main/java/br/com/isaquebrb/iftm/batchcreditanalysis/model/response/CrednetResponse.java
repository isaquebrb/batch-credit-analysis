package br.com.isaquebrb.iftm.batchcreditanalysis.model.response;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.credtnet.Crednet;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CrednetResponse {

    @JsonProperty("content")
    private Crednet crednet;

    private String date;

    private String hour;
}
