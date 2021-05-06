package br.com.isaquebrb.iftm.batchcreditanalysis.model.response;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DataResponse {

    @JsonProperty("content")
    private Data data;

    private String date;

    private String hour;
}
