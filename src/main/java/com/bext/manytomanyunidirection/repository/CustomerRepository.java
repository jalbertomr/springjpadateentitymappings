package com.bext.manytomanyunidirection.repository;

import com.bext.manytomanyunidirection.dto.CustomerItem;
import com.bext.manytomanyunidirection.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT new com.bext.manytomanyunidirection.dto.CustomerItem(c.id, c.name, i.id, i.name) FROM Customer c JOIN c.items i")
    List<CustomerItem> getCustomersItems();
}
