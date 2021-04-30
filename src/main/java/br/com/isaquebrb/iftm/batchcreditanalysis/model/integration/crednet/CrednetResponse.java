package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.crednet;

import lombok.Getter;

@Getter
public class CrednetResponse {

    private String date;

    private String hour;

    private CrednetResponseContent content;
}