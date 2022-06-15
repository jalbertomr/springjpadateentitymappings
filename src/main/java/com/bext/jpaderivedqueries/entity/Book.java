package com.bext.jpaderivedqueries.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book",schema = "dummy")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "dummy.book_seq")
    private Long id;
    private int version;
    private String title;
    @ManyToMany(mappedBy = "books", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(Author author){
       this.getAuthors().add(author);
       author.getBooks().add(this);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", authors...=" + // + authors +
                '}';
    }
}
