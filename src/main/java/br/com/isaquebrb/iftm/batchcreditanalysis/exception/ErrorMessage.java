package br.com.isaquebrb.iftm.batchcreditanalysis.exception;

import lombok.Getter;

public enum ErrorMessage {

    DATABASE_ERROR("Erro no banco de dados."),
    SYSTEM_ERROR("Erro interno do sistema."),
    BUSINESS_ERROR("Erro de negócio."),
    ;

    ErrorMessage(String message){
        this.message = message;
    }

    @Getter
    private String message;
}
