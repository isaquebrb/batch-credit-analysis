package br.com.isaquebrb.iftm.batchcreditanalysis.listener;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatus;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessorListener implements ItemProcessListener<CreditAnalysis, CreditAnalysis> {

    private final CreditAnalysisService service;

    @Override
    public void onProcessError(CreditAnalysis item, Exception e) {
        item.setStatus(AnalysisStatus.ERROR);
        item.setEndDate(LocalDateTime.now());
        item = service.save(item);
        log.info("Error on {}", item.toString());
    }

    @Override
    public void beforeProcess(CreditAnalysis item) {
        //do nothidng

    }

    @Override
    public void afterProcess(CreditAnalysis item, CreditAnalysis result) {
        //do nothing
    }
}
