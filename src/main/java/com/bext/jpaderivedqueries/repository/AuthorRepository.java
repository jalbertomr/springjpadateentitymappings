package com.bext.jpaderivedqueries.repository;

import com.bext.jpaderivedqueries.dto.AuthorBook;
import com.bext.jpaderivedqueries.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
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

    //Sort
    public List<Author> findByFirstNameContainingIgnoreCaseOrderByYearAsc(String partOfFirstName);
    public List<Author> findByFirstNameContainingIgnoreCaseOrderByYearDesc(String partOfFirstName);
    public List<Author> findByFirstNameContainingIgnoreCaseOrderByBooksTitle(String partOfFirstName);
    public List<Author> findByFirstNameContainingIgnoreCase(String partOfFirstName, Sort sort);

    //Limiting
    public List<Author> findFirst2ByFirstNameContainingIgnoreCaseOrderByYear(String partOfFirstName);

    //Paging
    public Page<Author> findAll(Pageable pageable);

    // SpEL Expressions for entityName and Advanced Like expressions
    @Query("FROM Author a WHERE a.firstName = ?1" )
    public List<Author> findByFirstNameSpEL_param( String firstName);

    @Query("FROM Author a WHERE a.firstName = :firstName" )
    public List<Author> findByFirstNameSpEL_paramname( String firstName);

    @Query("SELECT a FROM Author a WHERE a.year = ?#{[0]}")
    public List<Author> findAuthorByYearSpEL(int year);

    @Query("FROM Author WHERE UPPER(firstName) LIKE %?#{[0].toUpperCase()}%")
    public List<Author> findAuthorByfirstNameLikeSpel(String firstName);

    @Query("SELECT a FROM #{#entityName} a WHERE a.year > 2000")
    public List<Author> findByYear_entityName();

    // Native Query
    @Query(value = "SELECT * FROM dummy.author WHERE first_name = :name", nativeQuery = true)
    public List<Author> findAuthorByFirstName_Native(String name);

    @Modifying
    @Transactional
    @Query("DELETE Author a WHERE a.firstName = ?1" )
    public void deleteByfirstName(String firstName);

    @Modifying
    @Transactional
    @Query("UPDATE Author SET firstName = :prefix || firstName")
    public void addPrefixTofirstName(@Param("prefix") String prefix);

    // DTO projection for readonly to load on sp ecific dto class better performance
    @Query("SELECT new com.bext.jpaderivedqueries.dto.AuthorBook(a.firstName,b.title) FROM Author a JOIN a.books b")
    public List<AuthorBook> getAuthorBookList();


}
