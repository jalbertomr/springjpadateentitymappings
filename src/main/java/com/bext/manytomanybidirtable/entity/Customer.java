package com.bext.manytomanybidirtable.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer", schema = "dummy")
/*
@NamedNativeQueries({
        @NamedNativeQuery(name="getAllCustomersItems",
                query = "select c.id as C_ID, c.name as C_NAME, i.id as I_ID, i.name as I_NAME from dummy.customer c, dummy.item i, dummy.customer_item_set ci " +
                        " where ci.customer_set_id == c.id and ci.item_set_id = i.id"
        )
})*/
//@NamedNativeQuery(name="myfindByName", query = "select * from dummy.customer c where c.name = :qname", resultClass = Customer.class)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.customer_seq")
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.ALL})
    @JoinTable(name = "customer_item_set",schema = "dummy",            // can be omitted default values
            joinColumns = {@JoinColumn(name = "customer_set_id")},     // can be omitted default values
            inverseJoinColumns = {@JoinColumn(name = "item_set_id")})  // can be omitted default values
    private Set<Item> itemSet = new HashSet<>();

    public Customer() { }

    public Customer(String name) {
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

    public void addItem(Item item){
        this.itemSet.add(item);
        item.getCustomerSet().add(this);
    }

    /*
         CustomerOwn   <---> ItemSet[ item-A, item-B, item-ToRemove,...]
         item-ToRemove <---> CustomerSet[ Customer1, customer2, customerOwn_RemoveToo]
     */
    public void removeItem(Long itemId){
        Item item = this.itemSet.stream().filter(item1 -> item1.getId() == itemId).findFirst().orElse(null);
        if (item != null){
            this.itemSet.remove(item);
            item.getCustomerSet().remove(this);
        }
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' + ", items...}"
                ;
    }
}
