package com.bext.onetooneunidirectionInverse.repository;

import com.bext.onetooneunidirectionInverse.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
