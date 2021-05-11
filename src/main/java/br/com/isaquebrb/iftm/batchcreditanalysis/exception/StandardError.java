package br.com.isaquebrb.iftm.batchcreditanalysis.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StandardError {

    private Integer status;
    private List<String> errors;
    private String message;
}
