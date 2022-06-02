package com.bext.onetooneunidirectionInverse;

import com.bext.onetooneunidirectionInverse.entity.Customer;
import com.bext.onetooneunidirectionInverse.entity.Item;
import com.bext.onetooneunidirectionInverse.repository.CustomerRepository;
import com.bext.onetooneunidirectionInverse.repository.ItemRepository;
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
        /* @OneToOne Unidirection Inverse
           The field item.customer_id_fk makes possible from BD to have many Items for one customer
           so this is a OneToMany constructed with two OneToOne, BUT hibernate check if customer is logically detached

        class Customer {                               create table dummy.customer (
           Long id;                                            id         bigint not null,
           String name;                                        name       varchar(255),
           @OneToOne(mappedBy = "customer")                               primary key (id)
           Item item;                                          )
        }
        class Item {                                   create table dummy.item (
           Long id;                                            id         bigint not null,
           String name;                                        name       varchar(255),
           @OneToOne                                           customer_id_fk bigint,
           @JoinColumn(name = "customer_id_fk",
                       referencedColumnName = "id")
           Customer customer;                                  primary key (id)
        }                                                      )

         given  new Customer , new Item ->  customer set [Item] -> save Customer
         then   item saved

        Item can set and get Customer.
        */
        // Create customer - item relation and save by customer
        Customer customer = new Customer();
        customer.setName("Jose Alberto");
        Item item = new Item();
        item.setName("Item-A");

        customer.setItem( item);
        item.setCustomer(customer);

        Customer savedCustomer = customerRepository.save(customer);

        log.info("savedCustomer {} {} {}", savedCustomer.getId(), savedCustomer.getName(), savedCustomer.getItem().getName());

        // At this moment item has not been explicity saved, so when ItemRecovered tries to getCustomer.name will fail
        // IF Item-customer field OneToOne is fetch.LAZY, with fetch.EAGER saves item immediately when customer is saved.
        Item itemRecovered = itemRepository.findById(savedCustomer.getItem().getId()).get();
        log.info("itemRecovered: {} {} {}", itemRecovered.getId(), itemRecovered.getName(), itemRecovered.getCustomer().getName());

        // Saving item for sure can recover Customer indepently of fetch.LAZY
        Item savedItem = itemRepository.save(item);
        log.info("savedItem: {}", savedItem.getCustomer().getName());

        // Create new Customer - Item relation and save by item.
        Customer customer2 = new Customer();
        customer2.setName("Victoria");

        Item itemB = new Item();
        itemB.setName("Item-B");
        itemB.setCustomer(customer2);
        //itemB.setCustomer(customer);   // This will generate error: detached entity passed to persist because OneToOne violated
        itemRepository.save(itemB);


    }
}
