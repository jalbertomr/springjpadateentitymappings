package com.bext.onetooneunidirection;

import com.bext.onetooneunidirection.entity.Customer;
import com.bext.onetooneunidirection.entity.Item;
import com.bext.onetooneunidirection.repository.ItemRepository;
import com.bext.onetooneunidirection.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class SpringdatajpaentitymappingApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaentitymappingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /* @OneToOne Unidirection
        class Customer {                                      create table dummy.customer (
            long   id;                                         id         bigint not null,
            String name;                                       name       varchar(255),
            @OneToOne
            Item   item;                                       item_id_fk bigint,
                                                                          primary key (id)
         )
         class Item {                                          create table dummy.item (
            long id;                                            id         bigint not null,
            String name;                                        name       varchar(255),
         }                                                      primary key (id)
                                                                )
         given  new Customer , new Item ->  customer set [Item] -> save Customer
         then   item saved
         test   recoverItem = item not directlu saved

        */
        Customer customer = new Customer("Jose Alberto");
        Item item = new Item("Item2");
        customer.setItem( item);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("savedCustomer {}", savedCustomer);

        Customer recoveredCustomer = customerRepository.findById(customer.getId()).get();
        log.info("recoveredCustomer: {}", recoveredCustomer);

        Item recoveredItem = itemRepository.findById(customer.getItem().getId()).get();
        log.info("recoveredItem: {}", recoveredItem);

        // given new Customer , new Item  -> item set customer IMPOSSIBLE -> save Item
        // never save customer

    }
}
