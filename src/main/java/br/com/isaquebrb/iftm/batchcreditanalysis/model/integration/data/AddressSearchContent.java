package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressSearchContent {

    @JsonProperty("uf")
    private String stateUf;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("endereco")
    private String address;

    @JsonProperty("bairro")
    private String district;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("complemento")
    private String complement;
}
