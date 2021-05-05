package br.com.isaquebrb.iftm.batchcreditanalysis.model.entity;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ParameterResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parameter")
public class Parameter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "string_value")
    private String stringValue;

    @Column(name = "integer_value")
    private Integer integerValue;

    @Column(name = "numeric_value")
    private BigDecimal numericValue;

    @Column(name = "boolean_value")
    private Boolean booleanValue;

    @Column(name = "active")
    private Boolean active;

    public ParameterResponse toDto() {
        return ParameterResponse.builder()
                .name(this.name)
                .description(this.description)
                .stringValue(this.stringValue)
                .integerValue(this.integerValue)
                .numericValue(this.numericValue)
                .booleanValue(this.booleanValue)
                .active(this.active).build();
    }
}
