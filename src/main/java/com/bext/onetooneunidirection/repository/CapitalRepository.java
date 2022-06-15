package com.bext.onetooneunidirection.repository;

import com.bext.onetooneunidirection.entity.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapitalRepository extends JpaRepository<Capital, Long> {
}
