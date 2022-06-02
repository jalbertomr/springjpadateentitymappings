package com.bext.onetooneunidirectionInverse.repository;

import com.bext.onetooneunidirectionInverse.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
