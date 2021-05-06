package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

import lombok.Getter;

public enum AnalysisValidationEnum {
    DOCUMENT_SITUATION(InformationTypeEnum.CREDNET), //"Situação do documento
    IS_PEP(InformationTypeEnum.PEP), //Se é cliente politicamente exposto
    MAXIMUM_VALUE_FINANCIAL_PENDENCY(InformationTypeEnum.CREDNET), //Valor máximo de pendências financeiras
    MAXIMUM_VALUE_BACEN_PENDENCY(InformationTypeEnum.CREDNET), //Valor máximo de cheques no Bacen
    MAXIMUM_VALUE_STATE_PROTEST(InformationTypeEnum.CREDNET), //Valor máximo de protestos estaduais
    MINIMUM_RATE_SCORE_SERASA(InformationTypeEnum.CREDNET), //Taxa mínima de score Serasa
    MAXIMUM_RATE_NON_PAYMENT(InformationTypeEnum.CREDNET), //Taxa máximo de inadimplencia
    AGE(InformationTypeEnum.DATA), //Idade mínima e máxima
    DEATH(InformationTypeEnum.CREDNET) //Se consta óbito
    ;

    AnalysisValidationEnum(InformationTypeEnum infoType) {
        this.infoType = infoType;
    }

    @Getter
    private InformationTypeEnum infoType;
}
