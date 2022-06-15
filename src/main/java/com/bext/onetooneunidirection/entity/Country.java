package com.bext.onetooneunidirection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "country", schema = "dummy")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="dummy.country_seq")
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "capital_id_fk",            // default "item_id", could by omitted
                referencedColumnName = "id")    // default could be omitted
    private Capital capital;                    // in DB -> item_id_fk    bigint H2

    public Country(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                '}';
    }
}

