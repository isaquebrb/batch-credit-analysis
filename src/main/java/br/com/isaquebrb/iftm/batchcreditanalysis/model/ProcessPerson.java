package br.com.isaquebrb.iftm.batchcreditanalysis.model;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.Crednet;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data.Data;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.pep.Pep;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProcessPerson {

    ProcessingHistory processingHistory;
    CreditAnalysis creditAnalysis;
    Crednet crednet;
    Data data;
    Pep pep;
}
