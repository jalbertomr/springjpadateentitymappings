package com.bext.jpaderivedqueries.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="author", schema = "dummy")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.author_seq")
    private Long id;
    private int version;
    @Column( nullable = false)
    private String firstName;
    private String lastName;
    private int year;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.ALL})   // author_books, authors_id, books_id  default table names
    @JoinTable( name = "author_book", schema = "dummy",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName = "id")
    )
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String firstName) {
        this.firstName = firstName;
    }

    public Author(String firstName, String lastName, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
    }

    public void addBook(Book book){
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", year=" + year +
                ", books=" + books +
                '}';
    }
}
