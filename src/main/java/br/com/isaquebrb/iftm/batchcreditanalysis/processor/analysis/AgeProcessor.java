package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.IntegerParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgeProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            Integer personAge = Integer.valueOf(item.getData().getName().getContent().getAge());

            Integer minAge = parameterService.getParameter(IntegerParameterEnum.MIN_AGE);
            Integer maxAge = parameterService.getParameter(IntegerParameterEnum.MAX_AGE);

            item.getProcessingHistory().setPersonAge(personAge);

            if (personAge >= minAge && personAge <= maxAge) {
                item.getProcessingHistory().setAgeAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            } else {
                throw new BusinessException("A idade não está entre o mínimo " + minAge + " e o máximo " + maxAge);
            }
        } catch (BusinessException e) {
            log.warn("[AgeProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setAgeAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[AgeProcessor.process] {}", e.getMessage(), e);
            item.getProcessingHistory().setAgeAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MINIMUM_AGE;
    }
}
