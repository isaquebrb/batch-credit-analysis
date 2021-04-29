package br.com.isaquebrb.iftm.batchcreditanalysis.processor.validator;


import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

public class DocumentValidator implements Validator<CreditAnalysis> {

    @Override
    public void validate(CreditAnalysis analysis) throws ValidationException {
        if (analysis.getDocument().isEmpty())
            throw new ValidationException("Fil");
    }
}
