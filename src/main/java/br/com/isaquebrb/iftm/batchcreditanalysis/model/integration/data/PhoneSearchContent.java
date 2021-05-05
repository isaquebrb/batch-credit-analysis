package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PhoneSearchContent {

    @JsonProperty("fixo")
    private List<Phone> landLines;

    @JsonProperty("celular")
    private List<Phone> cellphones;
}
