package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.NumericParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.Bacen;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class BacenPendencyProcessor implements AnalysisProcessor {

    private final ParameterService parameterService;

    @Override
    public ProcessPerson process(ProcessPerson item) throws Exception {
        try {
            Bacen bacen = item.getCrednet().getBacen();

            if (bacen.getHasInformation().trim().equalsIgnoreCase("SIM") &&
                    bacen.getContent().getBacenChecks() == null) {
                item.getProcessingHistory().setValueBacenChecks(BigDecimal.ZERO);
                item.getProcessingHistory().setBacenPendencyAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }

            BigDecimal maxBacenValue = parameterService.getParameter(NumericParameterEnum.MAX_VALUE_BACEN_CHECKS);

            BigDecimal personBacenValue = BigDecimal.valueOf(
                    bacen.getContent().getBacenChecks().stream()
                            .mapToDouble(d -> Double.parseDouble(d.getValue()))
                            .sum());

            item.getProcessingHistory().setValueBacenChecks(personBacenValue);

            if (personBacenValue.compareTo(maxBacenValue) >= 0) {
                throw new BusinessException("O valor de cheques sem fundo (" + personBacenValue + ") ultrapassou o limite (" + maxBacenValue + ")");
            } else {
                item.getProcessingHistory().setBacenPendencyAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            log.warn("[BacenPendencyProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setBacenPendencyAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[BacenPendencyProcessor.process] Documento {}. {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setBacenPendencyAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MAXIMUM_VALUE_BACEN_PENDENCY;
    }
}
