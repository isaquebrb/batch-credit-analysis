package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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
