package br.com.isaquebrb.iftm.batchcreditanalysis.processor.validator;


import br.com.isaquebrb.iftm.batchcreditanalysis.model.entity.CreditAnalysis;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import java.util.Optional;

@Slf4j
public class DocumentValidator implements Validator<CreditAnalysis> {

    @Override
    public void validate(CreditAnalysis analysis) throws ValidationException {
        String document = analysis.getDocument();

        Optional<PersonType> personType = PersonType.getPersonType(document);
        if (personType.isPresent()) {
            analysis.setPersonType(personType.get());
            analysis.setDocument(document);
        } else {
            throw new ValidationException("Invalid document number [" + document + "]");
        }
    }
}
