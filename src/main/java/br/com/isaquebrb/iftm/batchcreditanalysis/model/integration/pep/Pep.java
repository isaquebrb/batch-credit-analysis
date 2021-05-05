package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.pep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Pep {

    @JsonProperty("pep")
    private String pepConfirmation;

    @JsonProperty("documento")
    private String document;

    @JsonProperty("dados")
    private List<PepContent> pepContentList;
}
