package br.com.isaquebrb.iftm.batchcreditanalysis.model.dto;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.Crednet;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProcessPerson {

    CreditAnalysis creditAnalysis;
    Crednet crednet;
}
