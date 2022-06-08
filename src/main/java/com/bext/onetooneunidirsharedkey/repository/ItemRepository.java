package com.bext.onetooneunidirsharedkey.repository;

import com.bext.onetooneunidirsharedkey.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
