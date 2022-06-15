package com.bext.manytomanybidirtable.repository;

import com.bext.manytomanybidirtable.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("FROM Item i JOIN FETCH i.customerSet c WHERE i.id = :id")
    Item findItemsById_named(Long id);
    List<Item> findItemsByCustomerSetId(Long customerId);
}
