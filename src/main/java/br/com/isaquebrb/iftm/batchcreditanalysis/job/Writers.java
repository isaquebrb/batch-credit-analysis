package br.com.isaquebrb.iftm.batchcreditanalysis.job;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
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
                    analysisService.save(item);
                } catch (Exception e) {
                    log.error("[CustomWriter.documentWriter] Erro ao tentar salvar a analise {}", item.toString(), e);
                }
            }
        };
    }
}
