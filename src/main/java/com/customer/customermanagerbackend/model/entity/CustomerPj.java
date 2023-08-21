package com.customer.customermanagerbackend.model.entity;

import com.customer.customermanagerbackend.enums.CustomerType;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.CNPJ_IS_REQUIRED;
import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.IE_IS_REQUIRED;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_pj")
public class CustomerPj extends Customer {
    @NotBlank(message = CNPJ_IS_REQUIRED)
    private String cnpj;
    @NotBlank(message = IE_IS_REQUIRED)
    private String ie;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CustomerType type = CustomerType.PJ;
    private LocalDate registrationDate;

}
