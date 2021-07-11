package br.com.isaquebrb.iftm.batchcreditanalysis.model.dto;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreditAnalysisDTO {

    private Long id;

    private Long jobExecutionId;

    private String document;

    private AnalysisStatusEnum status;

    private PersonTypeEnum personType;

    private String rejectionReason;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endDate;

    private String email;

    private String phoneNumber;
}
