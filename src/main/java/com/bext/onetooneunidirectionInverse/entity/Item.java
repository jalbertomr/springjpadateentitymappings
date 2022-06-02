package com.bext.onetooneunidirectionInverse.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "item", schema = "dummy")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.item_seq")
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id_fk", referencedColumnName = "id")  // this could be omitted
    private Customer customer;

}
