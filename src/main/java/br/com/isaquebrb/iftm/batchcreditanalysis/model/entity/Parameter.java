package br.com.isaquebrb.iftm.batchcreditanalysis.model.entity;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.dto.ParameterResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "string_value")
    private String stringValue;

    @Column(name = "integer_value")
    private Integer integerValue;

    @Column(name = "numeric_value")
    private Double numericValue;

    @Column(name = "boolean_value")
    private Boolean booleanValue;

    public ParameterResponse toDto() {
        ParameterResponse response = new ParameterResponse();
        response.setId(this.id);
        response.setName(this.name);
        response.setDescription(this.description);
        response.setStringValue(this.stringValue);
        response.setIntegerValue(this.integerValue);
        response.setNumericValue(this.numericValue);
        response.setBooleanValue(this.booleanValue);
        return response;
    }
}
