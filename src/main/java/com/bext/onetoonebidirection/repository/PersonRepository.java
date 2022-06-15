package com.bext.onetoonebidirection.repository;

import com.bext.onetoonebidirection.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
