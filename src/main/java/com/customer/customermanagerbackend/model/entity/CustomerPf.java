package com.customer.customermanagerbackend.model.entity;

import com.customer.customermanagerbackend.common.exception.helper.ErrorMessage;
import com.customer.customermanagerbackend.enums.CustomerType;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.CPF_IS_REQUIRED;
import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.RG_IS_REQUIRED;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_pf")
public class CustomerPf extends Customer {
    @NotBlank(message = CPF_IS_REQUIRED)
    private String cpf;
    @NotBlank(message = RG_IS_REQUIRED)
    private String rg;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CustomerType type = CustomerType.PF;
    private LocalDate registrationDate;
}
