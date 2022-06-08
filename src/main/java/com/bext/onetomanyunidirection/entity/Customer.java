package com.bext.onetomanyunidirection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Customer", schema = "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "dummy.customer_seq")
    private Long id;
    private String name;
    @OneToMany( cascade = CascadeType.ALL, targetEntity = Item.class)  //default targetEntity can be omitted
    @JoinColumn(name = "customer_id_fk",            // default is "items.items_id"
                referencedColumnName = "id")        // default could be omitted
    private List<Item> items = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }
}
