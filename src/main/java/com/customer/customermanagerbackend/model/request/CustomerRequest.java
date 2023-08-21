package com.customer.customermanagerbackend.model.request;

import com.customer.customermanagerbackend.model.entity.PhoneNumber;
import com.customer.customermanagerbackend.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.CNPJ_IS_INVALID;
import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.CPF_IS_INVALID;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class CustomerRequest {
    private String name;
    private CustomerType type;
    @CPF(message = CPF_IS_INVALID)
    private String cpf;
    @CNPJ(message = CNPJ_IS_INVALID)
    private String cnpj;
    private String rg;
    private String ie;
    private List<PhoneNumber> phoneNumbers;
}
