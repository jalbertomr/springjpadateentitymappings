package com.bext.manytomanybidirtable.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item", schema = "dummy")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.item_seq")
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "itemSet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})  //Default LAZY
    private Set<Customer> customerSet= new HashSet<>();

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public void addCustomer(Customer customer){
        this.customerSet.add(customer);
        customer.getItemSet().add(this);
    }

    public void removeCustomer(Long customerId){
        Customer customer = this.customerSet.stream()
                .filter(customer1 -> customer1.getId() == customerId).findFirst().orElse(null);
        if (customer != null){
            this.customerSet.remove(customer);
            customer.getItemSet().remove(this);
        }
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

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' + ", customers...}";
    }
}
