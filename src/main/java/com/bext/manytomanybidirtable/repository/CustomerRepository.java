package com.bext.manytomanybidirtable.repository;

import com.bext.manytomanybidirtable.dto.CustomerItem;
import com.bext.manytomanybidirtable.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomersByItemSetId(Long ItemId);
    Customer myFindByName(String name);
    @Query("SELECT new com.bext.manytomanybidirtable.dto.CustomerItem(c.name, i.name) FROM Customer c JOIN c.itemSet i")
    public List<CustomerItem> getJoinInformation();
}
