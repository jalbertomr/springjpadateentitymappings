package com.bext.manytomanybidirtable.repository;

import com.bext.manytomanybidirtable.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findItemsByCustomerSetId(Long customerId);
}
