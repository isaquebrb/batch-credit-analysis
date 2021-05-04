package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgeProcessor implements BaseAnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            log.info("[] PROCESS");
        } catch (Exception e) {
            log.error("[] ERRO", e);
        }
        return item;
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MINIMUM_AGE;
    }
}
