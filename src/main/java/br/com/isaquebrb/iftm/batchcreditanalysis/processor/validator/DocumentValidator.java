package br.com.isaquebrb.iftm.batchcreditanalysis.processor.validator;


import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.PersonType;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import java.time.LocalDateTime;

public class DocumentValidator implements Validator<CreditAnalysis> {

    @Override
    public void validate(CreditAnalysis analysis) throws ValidationException {
        analysis.setPersonType(PersonType.PF);
        analysis.setStartDate(LocalDateTime.now());
        if (analysis.getDocument().isEmpty())
            throw new ValidationException("Fil");
    }
}
