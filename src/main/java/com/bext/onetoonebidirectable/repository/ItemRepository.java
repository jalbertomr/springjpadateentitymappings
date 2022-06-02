package com.bext.onetoonebidirectable.repository;

import com.bext.onetoonebidirectable.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
