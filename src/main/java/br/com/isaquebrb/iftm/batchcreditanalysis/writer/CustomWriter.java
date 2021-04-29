package br.com.isaquebrb.iftm.batchcreditanalysis.writer;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomWriter {

    //private final CreditAnalysisService analysisService;

    public ItemWriter<CreditAnalysis> documentWriter() {
        return items -> {
            for (CreditAnalysis item : items) {
                System.out.print(item.getDocument() + ",");
                //analysisService.save(item);
            }
        };
    }
}
