package br.com.isaquebrb.iftm.batchcreditanalysis.model.entity;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.enums.AnalysisValidationEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "analysis_validation")
@NoArgsConstructor
public class AnalysisValidation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, name = "name")
    private AnalysisValidationEnum name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
