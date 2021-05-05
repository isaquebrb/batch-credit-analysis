package br.com.isaquebrb.iftm.batchcreditanalysis.processor.analysis;

import br.com.isaquebrb.iftm.batchcreditanalysis.exception.BusinessException;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessPerson;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.IntegerParameterEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.NumericParameterEnum;
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
            BigDecimal maxBacenValue = parameterService.getParameter(NumericParameterEnum.MAX_VALUE_BACEN_CHECKS);
            Integer maxBacenAmount = parameterService.getParameter(IntegerParameterEnum.MAX_AMOUNT_BACEN_CHECKS);

            Integer personBacenAmount = item.getCrednet().getBacen().getQuantity();
            BigDecimal personBacenValue = BigDecimal.valueOf(
                    item.getCrednet().getBacen().getContent().getBacenChecks().stream()
                            .mapToDouble(d -> Double.parseDouble(d.getValue()))
                            .sum());

            item.getProcessingHistory().setAmountBacenChecks(personBacenAmount);
            item.getProcessingHistory().setValueBacenChecks(personBacenValue);

            if (personBacenAmount > maxBacenAmount) {
                throw new BusinessException("A quantidade de cheques sem fundo (" + personBacenAmount
                        + ") foi maior que o permitido (" + maxBacenAmount + ")");
            } else if (personBacenValue.compareTo(maxBacenValue) >= 0) {
                throw new BusinessException("O valor de cheques sem fundo (" + personBacenValue
                        + ") foi maior que o permitido (" + maxBacenValue + ")");
            } else {
                item.getProcessingHistory().setBacenAnalysis(AnalysisStatusEnum.APPROVED);
                return item;
            }
        } catch (BusinessException e) {
            log.warn("[BacenPendencyProcessor.process] Documento {} {}", item.getCreditAnalysis().getDocument(), e.getMessage());
            item.getProcessingHistory().setBacenAnalysis(AnalysisStatusEnum.REJECTED);
            item.getCreditAnalysis().setRejectionReason(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[BacenPendencyProcessor.process] Documento {} {}", item.getCreditAnalysis().getDocument(), e.getMessage(), e);
            item.getProcessingHistory().setAgeAnalysis(AnalysisStatusEnum.ERROR);
            item.getCreditAnalysis().setRejectionReason("Erro desconhecido");
            throw e;
        }
    }

    @Override
    public AnalysisValidationEnum getEnumName() {
        return AnalysisValidationEnum.MINIMUM_VALUE_BACEN_PENDENCY;
    }
}
