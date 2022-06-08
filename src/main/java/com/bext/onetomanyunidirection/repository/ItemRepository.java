package com.bext.onetomanyunidirection.repository;

import com.bext.onetomanyunidirection.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
