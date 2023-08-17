package com.customer.customermanagerbackend.repository;

import com.customer.customermanagerbackend.entity.CustomerPf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPfRepository extends JpaRepository<CustomerPf, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);
}
