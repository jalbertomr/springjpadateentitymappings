package com.bext.onetomanyunidirtable.repository;

import com.bext.onetomanyunidirtable.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
