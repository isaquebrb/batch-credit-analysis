package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

public enum AnalysisValidationEnum {
    RF_DOCUMENT_SITUATION, //"Situação do documento na receita federal
    IS_PEP, //É cliente politicamente exposto
    MINIMUM_VALUE_FINANCIAL_PENDENCY, //Valor mínimo de pendências financeiras
    MINIMUM_VALUE_BACEN_PENDENCY, //Valor mínimo de pendências no Bacen
    MINIMUM_VALUE_STATE_PROTEST, //Valor mínimo de protestos estaduais
    MINIMUM_RATE_SCORE_SERASA, //Taxa mínima de score Serasa
    MINIMUM_RATE_NON_PAYMENT, //Taxa miníma de inadimplencia
    MINIMUM_AGE; //Idade mínima
}
