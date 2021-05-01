package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

import br.com.isaquebrb.iftm.batchcreditanalysis.utils.RegexUtils;
import lombok.Getter;

import java.util.Optional;

public enum PersonType {
    PF(11),
    PJ(14);

    PersonType(Integer numberLength) {
        this.numberLength = numberLength;
    }

    @Getter
    private Integer numberLength;

    public static Optional<PersonType> getPersonType(String document) {
        document = RegexUtils.removeNonNumeric(document);

        if (document.length() == PersonType.PF.getNumberLength())
            return Optional.of(PersonType.PF);
        else if (document.length() == PersonType.PJ.getNumberLength())
            return Optional.of(PersonType.PJ);

        return Optional.empty();
    }
}
