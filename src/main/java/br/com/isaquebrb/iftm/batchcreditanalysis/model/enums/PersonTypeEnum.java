package br.com.isaquebrb.iftm.batchcreditanalysis.model.enums;

import br.com.isaquebrb.iftm.batchcreditanalysis.utils.RegexUtils;
import lombok.Getter;

import java.util.Optional;

public enum PersonTypeEnum {
    PF(11),
    PJ(14);

    PersonTypeEnum(Integer numberLength) {
        this.numberLength = numberLength;
    }

    @Getter
    private Integer numberLength;

    public static Optional<PersonTypeEnum> getPersonType(String document) {
        document = RegexUtils.removeNonNumeric(document);

        if (document.length() == PersonTypeEnum.PF.getNumberLength())
            return Optional.of(PersonTypeEnum.PF);
        else if (document.length() == PersonTypeEnum.PJ.getNumberLength())
            return Optional.of(PersonTypeEnum.PJ);

        return Optional.empty();
    }
}
