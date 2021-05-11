package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.exception.SystemException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.parameter.IntegerParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum.*;
import static br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum.AGE;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgeProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            Integer personAge = Integer.valueOf(item.getDataResponse().getData().getName().getContent().getAge());

            Integer minAge = parameterService.getParameter(IntegerParameterEnum.MIN_AGE);
            Integer maxAge = parameterService.getParameter(IntegerParameterEnum.MAX_AGE);

            item.getCreditAnalysis().getProcessingHistory().setPersonAge(personAge);

            if (personAge >= minAge && personAge <= maxAge) {
                addStatus(item, AGE, APPROVED);
                return item;
            } else {
                throw new BusinessException("A idade (" + personAge + ") nao condiz com o minimo " + minAge + " e o maximo " + maxAge);
            }
        } catch (BusinessException e) {
            addStatus(item, AGE, REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            addStatus(item, AGE, ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AGE;
    }
}
