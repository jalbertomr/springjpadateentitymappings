package com.bext.onetoonebidirectable;

import com.bext.onetoonebidirectable.entity.Customer;
import com.bext.onetoonebidirectable.entity.Item;
import com.bext.onetoonebidirectable.repository.CustomerRepository;
import com.bext.onetoonebidirectable.repository.ItemRepository;
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
        /* OneToOne bidirection with Table
        class Customer {                        create table dummy.customer (
            Long id;                                    id    bigint not null,
            String name;                                name  varchar(255),
            @OneToOne                                       primary key (customer_id)
            @JoinTable(name = "customer_item")          )
            Item item;
        }

        create table dummy.customer_item (
                item_id int8,
                customer_id int8 not null,
                  primary key (customer_id)
               )

        class Item {                             create table dummy.item {
             Long id;                                    id    bigint not null,
             String name;                                name  varchar(255),
             @OneToOne( mappedBy = "item")                    primary key (item_id)
             Customer customer;                         )
        }

        */
        // Create Customer - Item and save by Customer
        Customer customer = new Customer("Jose Alberto");
        Item item = new Item("Item-A");
        customer.setItem( item);
        Customer savedCustomer = customerRepository.save(customer);

        log.info("savedCustomer {}", savedCustomer);
        Customer customerLoaded = customerRepository.findById(1L).get();

        // To Retrieve Customer-Item the Customer-Item must be fetch.EAGER (the default)
        log.info("customerLoaded {}", customerLoaded.getItem().getName());

        // Create new Customer - Item and save by Item Will NOT create Customer-Item relation in table
        Customer customer2 = new Customer("Victoria");
        Item itemB = new Item("item-B");

        //itemB.setCustomer(customer2);  // This Way will NOT create Custom-Item relation in table
        //itemRepository.save(itemB);

        customer2.setItem(itemB);
        customerRepository.save(customer2);
    }
}
