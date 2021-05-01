package br.com.isaquebrb.iftm.batchcreditanalysis.processor.validator;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
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
