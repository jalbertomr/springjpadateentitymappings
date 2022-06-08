package com.bext.onetooneunidirection.repository;


import com.bext.onetooneunidirection.dto.CustomerItem;
import com.bext.onetooneunidirection.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;
import static org.springframework.http.HttpHeaders.FROM;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT new com.bext.onetooneunidirection.dto.CustomerItem(c.name,i.name) FROM Customer c JOIN c.item i")
    public List<CustomerItem> getJoinCustomerItem();
}
