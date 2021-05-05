package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AddressSearch extends CommonInfo {

    @JsonProperty("conteudo")
    private List<AddressSearchContent> content;
}
