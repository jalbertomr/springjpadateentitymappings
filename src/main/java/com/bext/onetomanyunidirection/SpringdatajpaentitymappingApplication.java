package com.bext.onetomanyunidirection;

import com.bext.onetomanyunidirection.dto.CustomerItem;
import com.bext.onetomanyunidirection.entity.Customer;
import com.bext.onetomanyunidirection.entity.Item;
import com.bext.onetomanyunidirection.repository.CustomerRepository;
import com.bext.onetomanyunidirection.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootApplication
public class SpringdatajpaentitymappingApplication implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository itemRepository;
    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run( SpringdatajpaentitymappingApplication.class, args );
    }

    @Override
    public void run(String... args) throws Exception {
        /* @OneToMany Unidirectional with @JoinColumn avoids create table customer_items

        class Customer {                               create table dummy.customer (
           Long id;                                            id         bigint not null,
           String name;                                        name       varchar(255),
           @OneToMany                                              primary key (id)
           List<Item>items = new ArrayList<>();        )
        }

        class Item {                                   create table dummy.item (
           Long id;                                            id         bigint not null,
           String name;                                        name       varchar(255),
        }                                                       customer_id bigint,
                                                                   primary key (id)
                                                              )

        */
        createCustomerItems();
        findCustomerAddItem();
    }

    @Transactional
    public void createCustomerItems(){
        Customer customer = new Customer();
        customer.setName("Jose Alberto");
        Item item = new Item();
        item.setName("Item-A");
        Item itemB = new Item("Item-B");
        customer.getItems().add(item);
        customer.getItems().add(itemB);

        Customer customerSaved = customerRepository.save(customer);
        log.info("customerSaved {}",customerSaved);

        List<CustomerItem> customerItemList = customerRepository.getJoinCustomerItem();
        customerItemList.forEach(e -> log.info("customerItem: {}", e.toString()));
    }

    @Transactional
    public void findCustomerAddItem(){
        log.info("findCustomerAddItem");

        Customer _customer = customerRepository.findById_named(1L);
        Item itemX = new Item("Item-X");
        //itemRepository.save(itemX);

        _customer.getItems().add(itemX);
        customerRepository.save(_customer);

        List<CustomerItem> customerItemList = customerRepository.getJoinCustomerItem();
        customerItemList.forEach(e -> log.info("customerItem: {}", e.toString()));
    }
}
