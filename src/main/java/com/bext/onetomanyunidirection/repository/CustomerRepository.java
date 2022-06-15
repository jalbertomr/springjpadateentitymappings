package com.bext.onetomanyunidirection.repository;

import com.bext.onetomanyunidirection.dto.CustomerItem;
import com.bext.onetomanyunidirection.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //@Query("FROM Customer c JOIN FETCH c.items i WHERE c.id = :id")
    public Customer findById_named(Long id);

    @Query("SELECT new com.bext.onetomanyunidirection.dto.CustomerItem(c.name,i.name) FROM Customer c JOIN c.items i")
    public List<CustomerItem> getJoinCustomerItem();
}
