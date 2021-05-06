package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.pep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Pep {

    @JsonProperty("pep")
    private String pepConfirmation;

    @JsonProperty("documento")
    private String document;

    @JsonProperty("dados")
    private List<PepContent> pepContentList;
}
