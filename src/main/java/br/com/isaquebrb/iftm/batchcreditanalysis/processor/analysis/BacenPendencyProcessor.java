package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import org.springframework.stereotype.Component;

@Component
public class BacenPendencyProcessor implements BaseAnalysisProcessor {
    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        return null;
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return null;
    }
}
