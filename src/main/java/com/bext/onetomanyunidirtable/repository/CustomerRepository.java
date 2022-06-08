package com.bext.onetomanyunidirtable.repository;

import com.bext.onetomanyunidirtable.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
