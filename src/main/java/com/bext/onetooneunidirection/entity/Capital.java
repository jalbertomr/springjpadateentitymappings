package com.bext.onetooneunidirection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table( name = "capital", schema = "dummy")
public class Capital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.capital_seq")
    private Long id;
    private String name;

    public Capital(String name) {
        this.name = name;
    }
}
