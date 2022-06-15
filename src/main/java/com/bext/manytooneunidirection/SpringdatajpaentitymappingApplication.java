package com.bext.manytooneunidirection;

import com.bext.manytooneunidirection.entity.Customer;
import com.bext.manytooneunidirection.entity.Item;
import com.bext.manytooneunidirection.repository.CustomerRepository;
import com.bext.manytooneunidirection.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.List;
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
        /* @ManyToOne Unidirection

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


        //log.info("savedItem: {}", savedItem.getCustomer().getName());
/*

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
*/
        //customerItemRelationSaveCustomer();
        customerItemRelationSaveItem();
        //loadData();
        //itemFindById_checkCustomer();
        //itemFindById_named_checkCustomer();
    }

    @Transactional
    public void loadData(){
        itemRepository.deleteAll();

        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        itemA.setCustomer(customer1);
        itemRepository.save(itemA);
    }

    @Transactional
    public void customerItemRelationSaveCustomer(){
        customerRepository.deleteAll();

        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        itemA.setCustomer(customer1);
        customerRepository.save(customer1);
        List<Item> all = itemRepository.findAll();
        log.info("items: {}", all);          // No item saved
    }

    @Transactional
    public void customerItemRelationSaveItem(){
        customerRepository.deleteAll();

        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        itemA.setCustomer(customer1);
        Item _item = itemRepository.save(itemA);

        Item itemfinded = itemRepository.findById( _item.getId()).get();
        Customer customer = itemfinded.getCustomer();
        log.info("itemfinded.getCustomer() is HibernateProxy: {}", (customer instanceof HibernateProxy) ? "YES" : "NO");
        log.info("itemfinded.getCustomer() is initialized: {}", Hibernate.isInitialized( customer));

        customer = customerRepository.findById(customer.getId()).get();

        log.info("itemfinded.getCustomer() is HibernateProxy: {}", (customer instanceof HibernateProxy) ? "YES" : "NO");
        log.info("itemfinded.getCustomer() is initialized: {}", Hibernate.isInitialized( customer));

        log.info("itemfinded _customer: {}", customer);
        log.info("itemfinded: {}", itemfinded);          // No item saved
    }

    @Transactional
    public void itemFindById_checkCustomer(){
        log.info("itemFindBycheckcustomer");
        Item itemfinded = itemRepository.findById( 1L).get();
        Customer customer = itemfinded.getCustomer();      // gets customer proxy

        log.info("itemfinded.getCustomer() is HibernateProxy: {}", (customer instanceof HibernateProxy) ? "YES" : "NO");
        log.info("itemfinded.getCustomer() is initialized: {}", Hibernate.isInitialized( customer));
        //String name = _itemCustomer.getName(); //LazyInitializationException: could not initialize proxy [com.bext.manytooneunidirection.entity.Customer#1] - no Sessio

        //customer = customerRepository.findById(customer.getId()).get(); // initialize customer, proxy -> entity
        Hibernate.initialize(itemfinded.getCustomer());                   // initialize customer, proxy -> entity

        log.info("itemfinded.getCustomer() is HibernateProxy: {}", (customer instanceof HibernateProxy) ? "YES" : "NO");
        log.info("itemfinded.getCustomer() is initialized: {}", Hibernate.isInitialized( customer));

        log.info("itemfinded.getCustomer: {}", customer);
        log.info("itemfinded.getName(): {}", itemfinded);
    }

    @Transactional
    public void itemFindById_named_checkCustomer(){  //named query with JOIN FETCH
        log.info("itemFindBycheck_named_checkCustomer");
        Item itemfinded = itemRepository.findById_named( 1L);
        Customer customer = itemfinded.getCustomer();      // gets customer

        log.info("itemfinded.getCustomer() is HibernateProxy: {}", (customer instanceof HibernateProxy) ? "YES" : "NO");
        log.info("itemfinded.getCustomer() is initialized: {}", Hibernate.isInitialized( customer));

        log.info("itemfinded.getCustomer: {}", customer);
        log.info("itemfinded.getName(): {}", itemfinded);
    }
}
