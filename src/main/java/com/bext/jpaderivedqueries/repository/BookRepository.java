package com.bext.jpaderivedqueries.repository;

import com.bext.jpaderivedqueries.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
