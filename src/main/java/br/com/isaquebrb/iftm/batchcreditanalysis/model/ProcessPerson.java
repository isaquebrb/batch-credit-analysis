package br.com.isaquebrb.iftm.batchcreditanalysis.model;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.CrednetResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.DataResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.PepResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProcessPerson {
    CreditAnalysis creditAnalysis;
    CrednetResponse crednetResponse;
    DataResponse dataResponse;
    PepResponse pepResponse;
}
