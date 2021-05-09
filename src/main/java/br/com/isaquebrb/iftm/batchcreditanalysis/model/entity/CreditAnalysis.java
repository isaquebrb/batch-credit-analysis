package br.com.isaquebrb.iftm.batchcreditanalysis.model.entity;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.ProcessingHistory;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisStatusEnum;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.PersonTypeEnum;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "credit_analysis")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class CreditAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document")
    private String document;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private AnalysisStatusEnum status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "person_type")
    private PersonTypeEnum personType;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "start_process_date")
    private LocalDateTime startDate;

    @Column(name = "end_process_date")
    private LocalDateTime endDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Type(type = "jsonb")
    @Column(name = "processing_history", columnDefinition = "jsonb")
    private ProcessingHistory processingHistory = new ProcessingHistory();
}