package com.bext.onetooneunidirection.repository;


import com.bext.onetooneunidirection.dto.CountryCapital;
import com.bext.onetooneunidirection.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("SELECT new com.bext.onetooneunidirection.dto.CountryCapital(c.name,i.name) FROM Country c JOIN c.capital i")
    public List<CountryCapital> getJoinCountryCapital();
}
