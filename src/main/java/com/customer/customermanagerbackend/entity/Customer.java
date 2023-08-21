package com.customer.customermanagerbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
@SQLDelete(sql = "UPDATE customer SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isActive = true;
    @OneToMany()
    @JoinColumn(name = "CUSTOMER_ID")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

}
