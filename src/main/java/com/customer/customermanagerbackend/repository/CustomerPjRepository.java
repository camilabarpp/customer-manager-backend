package com.customer.customermanagerbackend.repository;

import com.customer.customermanagerbackend.entity.CustomerPj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPjRepository extends JpaRepository<CustomerPj, Long> {
}
