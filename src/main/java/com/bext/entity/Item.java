package com.bext.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "item_seq")
    private Long id;
    private String name;

    public Item(String name) {
        this.name = name;
    }
}
