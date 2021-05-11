package br.com.isaquebrb.iftm.batchcreditanalysis.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.JobParameters;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class JobRequest {

    @NotNull(message = "Nome do job é obrigatório")
    String jobName;

    JobParameters parameters;
}
