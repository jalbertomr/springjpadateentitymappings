package com.bext.manytooneunidirection.repository;

import com.bext.manytooneunidirection.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
