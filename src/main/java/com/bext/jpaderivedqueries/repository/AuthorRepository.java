package com.bext.jpaderivedqueries.repository;

import com.bext.jpaderivedqueries.dto.AuthorBook;
import com.bext.jpaderivedqueries.entity.Author;
import com.bext.jpaderivedqueries.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public List<Author> findByFirstName(String firstName);
    public List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    public List<Author> findByFirstNameContainingIgnoreCase(String partOfFirstName);
    public List<Author> findByFirstNameLike(String firstNameLike);
    public List<Author> findByYearBefore(int year);
    public List<Author> findByYearBetween(int yearStart, int yearEnd);
    public List<Author> findByYearLessThanOrYearGreaterThan( int year, int year2);

    // Traverse Association in derived query
    public List<Author> findByBooksTitle(String title);
    public List<Author> findByYearLessThanEqualAndBooksTitleContainsIgnoreCase(int year, String partOfTitle);

    //Order
    public List<Author> findByFirstNameContainingIgnoreCaseOrderByYearAsc(String partOfFirstName);
    public List<Author> findByFirstNameContainingIgnoreCaseOrderByYearDesc(String partOfFirstName);
    public List<Author> findByFirstNameContainingIgnoreCaseOrderByBooksTitle(String partOfFirstName);
    public List<Author> findByFirstNameContainingIgnoreCase(String partOfFirstName, Sort sort);

    //Limiting
    public List<Author> findFirst2ByFirstNameContainingIgnoreCaseOrderByYear(String partOfFirstName);

    //Paging
    public Page<Author> findAll(Pageable pageable);

    @Query("SELECT new com.bext.jpaderivedqueries.dto.AuthorBook(a.firstName,b.title) FROM Author a JOIN a.books b")
    public List<AuthorBook> getAuthorBookList();


}
