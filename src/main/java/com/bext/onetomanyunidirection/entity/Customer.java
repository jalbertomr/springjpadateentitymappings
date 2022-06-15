package com.bext.onetomanyunidirection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name="Customer.findById_named", query = " FROM Customer c JOIN FETCH c.items i WHERE c.id = :id")
@Table(name = "Customer", schema = "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "dummy.customer_seq")
    private Long id;
    private String name;
    @OneToMany( cascade = CascadeType.ALL, targetEntity = Item.class)  //default targetEntity can be omitted
    //@JoinColumn(name = "customer_id_fk",            // default is "items.items_id" And Avoids the creation of extra Table
    //            referencedColumnName = "id")        // default could be omitted
    private List<Item> items = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
