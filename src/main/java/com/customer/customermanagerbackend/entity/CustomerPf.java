package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.enums.CustomerType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_pf")
public class CustomerPf extends Customer {
    private String cpf;
    private String rg;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CustomerType type = CustomerType.PF;
    private LocalDate registrationDate;
}
