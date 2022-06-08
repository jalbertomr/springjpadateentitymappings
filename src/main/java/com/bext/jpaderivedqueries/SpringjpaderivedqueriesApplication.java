package com.bext.jpaderivedqueries;

import com.bext.jpaderivedqueries.dto.AuthorBook;
import com.bext.jpaderivedqueries.entity.Author;
import com.bext.jpaderivedqueries.entity.Book;
import com.bext.jpaderivedqueries.repository.AuthorRepository;
import com.bext.jpaderivedqueries.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringjpaderivedqueriesApplication implements CommandLineRunner {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
      SpringApplication.run(SpringjpaderivedqueriesApplication.class , args);
    }

    @Override
    public void run(String... args) throws Exception {
        findByXXX();
        //test1();
    }

    @Transactional
    public void loadData(){
        Author author1 = new Author("Jose Alberto", "Martinez", 1970);
        Book bookA = new Book("Hibernate JPA");
        Book bookB = new Book( "Spring Boot");
        Book bookC = new Book("Soberania");
        author1.addBook(bookA);
        author1.addBook(bookB);
        author1.addBook(bookC);
        authorRepository.save(author1);

        Author author2 = new Author("Victoria","Alada", 2000);
        Book bookD = new Book("Soberania");
        author2.addBook(bookD);
        authorRepository.save(author2);

        Author author3 = new Author("Alba","Celeste", 2020);
        Book bookX = new Book("La Naturaleza");
        author3.addBook(bookX);
        authorRepository.save(author3);

        Author author4 = new Author("Elmer","Gomez", 2022);
        Book bookY = new Book("El Libro");
        author3.addBook(bookY);
        authorRepository.save(author4);

        Author author5 = new Author("Sofia","Powers", 2022);
        Book bookZ = new Book("Ciberntética");
        author3.addBook(bookZ);
        authorRepository.save(author5);

    }

    @Transactional
    public void test1(){
        this.loadData();
        List<AuthorBook> authorBookList = authorRepository.getAuthorBookList();
        authorBookList.forEach(e -> log.info("authorbook: {}", e.toString()));
    }

    @Transactional
    public void findByXXX(){
        this.loadData();

        List<Author> byFirstName = authorRepository.findByFirstName("Jose Alberto");
        log.info("byFirstName {}", byFirstName);

        List<Author> byFirstNameAndLastName = authorRepository.findByFirstNameAndLastName("Victoria", "Alada");
        log.info("byFirstNameAndLastName: {}", byFirstNameAndLastName);

        List<Author> byFirstNameContainingIgnoreCase = authorRepository.findByFirstNameContainingIgnoreCase("Albe");
        log.info("byFirstNameContainingIgnoreCase: {}", byFirstNameContainingIgnoreCase);

        List<Author> byFirstNameLike = authorRepository.findByFirstNameLike("%ose%");
        log.info("byFirstNameLike: {}", byFirstNameLike);

        List<Author> byYearBefore = authorRepository.findByYearBefore(2001);
        byYearBefore.forEach(e -> log.info("byYearBefore: {}", e.getFirstName() + " " + e.getLastName() + " " + e.getYear()));

        List<Author> byYearBetween = authorRepository.findByYearBetween(2015, 2030);
        byYearBetween.forEach(e -> log.info("byYearBetween: {}", e.getFirstName() + " " + e.getLastName() + " " + e.getYear()));

        List<Author> byYearLessThanEqualOrBooksGreaterThanEqual = authorRepository.findByYearLessThanOrYearGreaterThan(2000,2000);
        byYearLessThanEqualOrBooksGreaterThanEqual
                .forEach(e -> log.info("byYearLessThanEqualOrBooksGreaterThanEqual: {}", e.getFirstName() + " " + e.getLastName() + " " + e.getYear()));

        // Travese associations
        List<Author> byBooksTitle = authorRepository.findByBooksTitle("Soberania");
        byBooksTitle.forEach(e -> log.info("byBooksTitle: {}", e.getFirstName() + " " + e.getLastName()));

        List<Author> byYearLessThanEqualAndBooksTitleContainsIgnoreCase = authorRepository.findByYearLessThanEqualAndBooksTitleContainsIgnoreCase(2010, "ia");
        byYearLessThanEqualAndBooksTitleContainsIgnoreCase
                .forEach(e -> log.info("byYearLessThanEqualAndBooksTitleContainsIgnoreCase: {}", e.getFirstName() + " " + e.getLastName() + " " + e.getYear() +
                        " " + e.getBooks().stream().filter(book -> book.getTitle().contains("ia")).findFirst().orElse(null)));

        // Order
        List<Author> byFirstNameContainingIgnoreCaseOrderByYearAsc = authorRepository.findByFirstNameContainingIgnoreCaseOrderByYearAsc("a");
        byFirstNameContainingIgnoreCaseOrderByYearAsc.forEach( a -> log.info("autor year ascendent: {}", a.getFirstName() + " " + a.getYear() ));

        List<Author> byFirstNameContainingIgnoreCaseOrderByYearDesc = authorRepository.findByFirstNameContainingIgnoreCaseOrderByYearDesc("a");
        byFirstNameContainingIgnoreCaseOrderByYearDesc.forEach( a -> log.info("autor year descendent: {}", a.getFirstName() + " " + a.getYear() ));

        List<Author> findByFirstNameContainingIgnoreCaseOrderByBooksTitle = authorRepository.findByFirstNameContainingIgnoreCaseOrderByBooksTitle("a");
        findByFirstNameContainingIgnoreCaseOrderByBooksTitle.forEach( a -> log.info("autor order booksTitle: {}", a.getFirstName() + " " + a.getYear() +
                " " + a.getBooks().toString()
        ));

        Sort sortYearAsc = Sort.by("year").ascending().and(Sort.by("firstName").descending());
        List<Author> findByFirstNameContainingIgnoreCaseSortParameter = authorRepository.findByFirstNameContainingIgnoreCase("", sortYearAsc);
        findByFirstNameContainingIgnoreCaseSortParameter.forEach( a -> log.info("findByFirstNameContainingIgnoreCaseSortParameter: {}", a.getFirstName() + " " + a.getYear()));

        List<Author> findFirst2ByFirstNameContainingIgnoreCaseOrderByYear = authorRepository.findFirst2ByFirstNameContainingIgnoreCaseOrderByYear("a");
        findFirst2ByFirstNameContainingIgnoreCaseOrderByYear.forEach( a -> log.info("findFirst2ByFirstNameContainingIgnoreCaseOrderByYear: {}", a.getFirstName() + " " + a.getYear()));

        Pageable pageable = PageRequest.of(0 ,2);
        Page<Author> findAllPageable = authorRepository.findAll(pageable);
        log.info("findAllPageable.getTotalElements(): {}",findAllPageable.getTotalElements());
        log.info(" findAllPageable.getTotalPages() : {}", findAllPageable.getTotalPages());
        findAllPageable.getContent().forEach(author -> log.info("author: {}", author.getFirstName()));
        log.info("Take another page ");
        Pageable pageable1 = PageRequest.of(1, 2);
        Page<Author> findAllPageable1 = authorRepository.findAll(pageable1);
        findAllPageable1.getContent().forEach(author -> log.info("author: {}", author.getFirstName()));

    }
}