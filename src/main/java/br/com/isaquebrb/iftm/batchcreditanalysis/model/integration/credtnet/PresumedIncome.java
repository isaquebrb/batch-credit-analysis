package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.CommonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PresumedIncome extends CommonInfo {

    public PresumedIncome(String hasInfo, PresumedIncomeContent content){
        super(hasInfo);
        this.content = content;
    }

    @JsonProperty("conteudo")
    private PresumedIncomeContent content;
}
