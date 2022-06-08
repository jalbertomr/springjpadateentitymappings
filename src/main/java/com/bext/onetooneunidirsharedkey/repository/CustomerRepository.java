package com.bext.onetooneunidirsharedkey.repository;

import com.bext.onetooneunidirsharedkey.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
