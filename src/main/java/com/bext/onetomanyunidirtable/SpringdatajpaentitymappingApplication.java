package com.bext.onetomanyunidirtable;

import com.bext.onetomanyunidirtable.entity.Customer;
import com.bext.onetomanyunidirtable.entity.Item;
import com.bext.onetomanyunidirtable.repository.CustomerRepository;
import com.bext.onetomanyunidirtable.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        /* @OneToMany Unidirectional with Table

        class Customer {                               create table dummy.customer (
           Long id;                                            id         bigint not null,
           String name;                                        name       varchar(255),
           @OneToMany                                              primary key (id)
           List<Item>items = new ArrayList<>();        )
        }

        create table dummy.customer_items (
                 customer_id int8 not null,
                 item_id int8 not null
        )

        class Item {                                   create table dummy.item (
           Long id;                                            id         bigint not null,
           String name;                                        name       varchar(255),
        }                                                           primary key (id)
                                                              )

        */
        // Create customer - item relation and save by customer also saves the items.
        Customer customer = new Customer();
        customer.setName("Jose Alberto");
        Item item = new Item();
        item.setName("Item-A");
        Item itemB = new Item("Item-B");
        customer.getItems().add(item);
        customer.getItems().add(itemB);

        Customer customerSaved = customerRepository.save(customer);
        log.info("customerSaved {}",customerSaved);

    }
}
