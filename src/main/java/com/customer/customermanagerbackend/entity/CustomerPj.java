package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.enums.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_pj")
public class CustomerPj extends Customer {
    private String cnpj;
    private String ie;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CustomerType type = CustomerType.PJ;
    private LocalDate registrationDate;

}
