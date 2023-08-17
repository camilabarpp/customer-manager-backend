package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.enums.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_pj")
@SQLDelete(sql = "UPDATE customer_pj SET is_active = false WHERE id = ?")
public class CustomerPj extends Customer {
    private String cnpj;
    private String ie;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CustomerType type = CustomerType.PJ;
    private LocalDate registrationDate;
    private boolean isActive = true;

}
