package com.customer.customermanagerbackend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;

import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.NUMBER_IS_REQUIRED;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone_number")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = NUMBER_IS_REQUIRED)
    private String number;
}
