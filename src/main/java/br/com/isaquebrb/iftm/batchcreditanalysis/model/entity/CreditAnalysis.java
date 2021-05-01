package br.com.isaquebrb.iftm.batchcreditanalysis.model.entity;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatus;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonType;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.credtnet.Crednet;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "credit_analysis")
public class CreditAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document")
    private String document;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private AnalysisStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "person_type")
    private PersonType personType;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    //todo parameter table, process
}