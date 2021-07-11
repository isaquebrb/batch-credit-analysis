package br.com.isaquebrb.iftm.batchcreditanalysis.job;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.DatabaseException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreditAnalysisWriter implements ItemWriter<CreditAnalysis> {

    private Long jobExecutionId;

    private final CreditAnalysisService analysisService;

    @BeforeStep
    public void setJobExecutionId(StepExecution stepExecution) {
        this.jobExecutionId = stepExecution.getJobExecutionId();
    }

    @Override
    public void write(List<? extends CreditAnalysis> list) throws Exception {
        for (CreditAnalysis item : list) {
            try {
                item.setJobExecutionId(this.jobExecutionId);
                analysisService.save(item);
            } catch (DatabaseException e) {
                log.error("[CustomWriter.documentWriter] Erro ao tentar salvar a analise {}", item.toString(), e);
            }
        }
    }
}
