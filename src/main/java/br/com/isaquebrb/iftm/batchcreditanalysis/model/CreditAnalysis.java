package br.com.isaquebrb.iftm.batchcreditanalysis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class CreditAnalysis {

    private Long id;
    private String document;
    private AnalysisStatus status;
    private PersonType personType;
    private String rejectionReason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String email;
    private String cellphoneNumber;
}
