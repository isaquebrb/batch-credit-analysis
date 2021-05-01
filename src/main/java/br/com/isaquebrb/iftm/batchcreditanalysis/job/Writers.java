package br.com.isaquebrb.iftm.batchcreditanalysis.job;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatus;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class Writers {

    private final CreditAnalysisService analysisService;

    public ItemWriter<CreditAnalysis> documentWriter() {
        return items -> {
            for (CreditAnalysis item : items) {
                try {
                    item.setEndDate(LocalDateTime.now());
                    item.setStatus(AnalysisStatus.APPROVED);
                    analysisService.save(item);
                } catch (Exception e) {
                    log.error("[CustomWriter.documentWriter] Error trying to write {} object.", item.toString(), e);
                }
            }
        };
    }
}
