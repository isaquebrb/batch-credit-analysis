package br.com.isaquebrb.iftm.batchcreditanalysis.writer;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.AnalysisStatus;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomWriter {

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
