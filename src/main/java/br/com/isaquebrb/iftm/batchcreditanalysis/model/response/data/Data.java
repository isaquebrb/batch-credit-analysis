package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Data {

    @JsonProperty("nome")
    private Name name;

    @JsonProperty("pesquisa_enderecos")
    private AddressSearch addressSearch;

    @JsonProperty("pesquisa_telefones")
    private PhoneSearch phonesSearch;

    @JsonProperty("emails")
    private Email emails;
}
