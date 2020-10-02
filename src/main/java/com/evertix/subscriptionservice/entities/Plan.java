package com.evertix.subscriptionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@Entity
@Table(name = "plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Plan extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Tittle cannot be null")
    @NotBlank(message = "Tittle cannot be null")
    @Size(max = 150)
    private String tittle;

    // ONLY FOR STUDENT
    @Max(value = 500)
    private Short hours;

    @DecimalMin(value = "0.0")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    public Plan(String tittle, Short hours, BigDecimal price) {
        super();
        this.tittle=tittle;
        this.hours= hours;
        this.price=price;
    }
}
