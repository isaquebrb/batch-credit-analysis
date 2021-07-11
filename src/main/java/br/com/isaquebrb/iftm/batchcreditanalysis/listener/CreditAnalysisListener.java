package br.com.isaquebrb.iftm.batchcreditanalysis.listener;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.DatabaseException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Slf4j
@Component
@RequiredArgsConstructor
public class CreditAnalysisListener {

    private final CreditAnalysisService creditAnalysisService;

    private Long jobExecutionId;

    public void beforeProcess(CreditAnalysis creditAnalysis) {
        //do nothing
    }

    public void afterProcess(CreditAnalysis creditAnalysis, CreditAnalysis creditAnalysis2) {
        //do nothing
    }

    @OnProcessError
    public void onProcessError(CreditAnalysis item, Exception e) {
        if (e instanceof BusinessException) {
            log.warn("[CreditAnalysisListener.onProcessError] Documento {}. {}", item.getDocument(), e.getMessage());
        } else {
            log.error("[CreditAnalysisListener.onProcessError] Documento {}. {}", item.getDocument(), e.getMessage(), e);
        }

        saveAnalysis(item);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void saveAnalysis(CreditAnalysis item) {
        try {
            item.setEndDate(LocalDateTime.now());
            item.setJobExecutionId(this.jobExecutionId);
            creditAnalysisService.save(item);
        } catch (DatabaseException e) {
            log.error("[CreditAnalysisListener.onProcessError] Erro ao salvar o processamento do documento {}",
                    item.getDocument());
        }
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.jobExecutionId = stepExecution.getJobExecutionId();
    }
}
