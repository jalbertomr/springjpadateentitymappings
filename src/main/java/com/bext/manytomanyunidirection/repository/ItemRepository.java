package com.bext.manytomanyunidirection.repository;

import com.bext.manytomanyunidirection.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public Item findByName(String name);
}
