package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

import lombok.Getter;

public enum AnalysisValidationEnum {
    RF_DOCUMENT_SITUATION(InformationTypeEnum.CREDNET), //"Situação do documento na receita federal
    IS_PEP(InformationTypeEnum.PEP), //É cliente politicamente exposto
    MINIMUM_VALUE_FINANCIAL_PENDENCY(InformationTypeEnum.CREDNET), //Valor mínimo de pendências financeiras
    MINIMUM_VALUE_BACEN_PENDENCY(InformationTypeEnum.CREDNET), //Valor mínimo de pendências no Bacen
    MINIMUM_VALUE_STATE_PROTEST(InformationTypeEnum.CREDNET), //Valor mínimo de protestos estaduais
    MINIMUM_RATE_SCORE_SERASA(InformationTypeEnum.CREDNET), //Taxa mínima de score Serasa
    MINIMUM_RATE_NON_PAYMENT(InformationTypeEnum.CREDNET), //Taxa miníma de inadimplencia
    MINIMUM_AGE(InformationTypeEnum.DATA); //Idade mínima

    AnalysisValidationEnum(InformationTypeEnum infoType) {
        this.infoType = infoType;
    }

    @Getter
    private InformationTypeEnum infoType;
}
