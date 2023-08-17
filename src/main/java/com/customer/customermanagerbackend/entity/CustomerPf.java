package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.enums.CustomerType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_pf")
@SQLDelete(sql = "UPDATE customer_pj SET is_active = false WHERE id = ?")
public class CustomerPf extends Customer {
    private String cpf;
    private String rg;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CustomerType type = CustomerType.PF;
    private LocalDate registrationDate;
    private boolean isActive = true;
}
