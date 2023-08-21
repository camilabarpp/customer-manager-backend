package com.customer.customermanagerbackend.repository;

import com.customer.customermanagerbackend.model.entity.CustomerPj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPjRepository extends JpaRepository<CustomerPj, Long> {
    boolean existsByCnpj(String cnpj);
    boolean existsByIe(String ie);
}
