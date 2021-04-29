package br.com.isaquebrb.iftm.batchcreditanalysis.processor;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.processor.validator.DocumentValidator;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidatorProcessor {

    public ValidatingItemProcessor<CreditAnalysis> documentValidator() {
        ValidatingItemProcessor<CreditAnalysis> processor = new ValidatingItemProcessor<>();
        processor.setValidator(new DocumentValidator());
        processor.setFilter(true);
        return processor;
    }
}
