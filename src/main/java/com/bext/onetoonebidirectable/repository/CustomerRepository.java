package com.bext.onetoonebidirectable.repository;

import com.bext.onetoonebidirectable.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
