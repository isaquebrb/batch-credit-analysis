package br.com.isaquebrb.iftm.batchcreditanalysis.listener;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class CreditAnalysisListener extends ItemListenerSupport<CreditAnalysis, CreditAnalysis> {

    @Override
    public void onWriteError(Exception ex, List<? extends CreditAnalysis> item) {
        log.error("");
    }

    @Override
    public void onProcessError(CreditAnalysis item, Exception e) {
        log.error("");
    }
}
