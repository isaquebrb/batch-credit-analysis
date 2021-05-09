package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;

public class AnalysisEndProcessor implements ItemProcessor<ProcessPerson, CreditAnalysis> {

    @Override
    public CreditAnalysis process(ProcessPerson item) throws Exception {
        item.getCreditAnalysis().setStatus(AnalysisStatusEnum.APPROVED);
        item.getCreditAnalysis().setEndDate(LocalDateTime.now());
        return item.getCreditAnalysis();
    }
}
