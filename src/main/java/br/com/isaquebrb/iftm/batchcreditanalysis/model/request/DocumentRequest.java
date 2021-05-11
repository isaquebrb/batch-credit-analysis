package br.com.isaquebrb.iftm.batchcreditanalysis.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class DocumentRequest {

    @NotNull(message = "Documento é obrigatório")
    private String document;
}
