package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AddressSearch extends CommonInfo {

    @JsonProperty("conteudo")
    private List<AddressSearchContent> content;
}
