package com.bext.manytooneunidirection.repository;

import com.bext.manytooneunidirection.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findById_named(Long id);
}
