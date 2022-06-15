package com.bext.manytomanybidirtable;



import com.bext.manytomanybidirtable.dao.CustomerDao;
import com.bext.manytomanybidirtable.dto.CustomerItem;
import com.bext.manytomanybidirtable.entity.Customer;
import com.bext.manytomanybidirtable.entity.Item;
import com.bext.manytomanybidirtable.repository.CustomerRepository;
import com.bext.manytomanybidirtable.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class SpringdatajpaentitymappingApplication implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CustomerDao customerDao;

    public static void main(String[] args) {
        SpringApplication.run( SpringdatajpaentitymappingApplication.class, args );
    }


    @Override
    public void run(String... args) throws Exception {
        /*  @ManyToMany bidirectional with table
            This use annotation @ManyToMany on both tables

        class Customer {                              create table customer (
           Long id;                                            id   int8 not null,
           String name;                                        name varchar(255),
           @ManyToMany...
           Set<Item> itemSet = new HashSet<>();                     primary key (id)
        }                                             )

                                                  create table dummy.customer_item_set (
                                                             customer_set_id  int8 not null,
                                                             item_set_id      int8 not null,
                                                                 primary key (customer_set_id, item_set_id)
                                                  )

        class Item {                                    create table dummy.item (
           Long id;                                           id    int8 not null,
           String name;                                       name  varchar(255),
           @ManyToMany(mappedBy ="itemSet")                         primary key (id)
           Set<Customer> customerSet = new HashSet<>(); )
        }

        customerRepository.deleteAll() : delete all Customers and Items and bidirectional table
        itemRepository.deleteAll()     : delete all Items and Customers and bidirectional table
         */
        //customerRepository.deleteAll();
        //itemRepository.deleteAll();
        //test1();
        //insertCustomerItemsManyTimes();
        insertCustomerItemsthenFindItem();
        //setCustomerRepository_findCustomersByItemSetId();
        //customerDaoTest();
        //namedQuery();
    }

    @Transactional
    public void loadData(){
        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        customer1.addItem(itemA);
        Item itemB = new Item("Item-B");
        customer1.addItem(itemB);
        Item itemC = new Item("Item-C");
        customer1.addItem(itemC);
        Item itemD = new Item("Item-D");
        customer1.addItem(itemD);
        Customer _customer1 = customerRepository.save(customer1);
    }

     @Transactional
     public void test1(){

         // add customer1 & itemA
        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        customer1.addItem(itemA);
        Item itemB = new Item("Item-B");
        customer1.addItem(itemB);
        Item itemC = new Item("Item-C");
        customer1.addItem(itemC);
        Item itemD = new Item("Item-D");
        customer1.addItem(itemD);
        customer1.getItemSet().forEach(e -> log.info("item: {}", e.toString() ));

        Customer _customer1 = customerRepository.save(customer1);
        log.info("_customer1 {}", _customer1.toString());

        Customer customerFinded = customerRepository.findById(_customer1.getId()).get();
        log.info("customerRecovered: {}", customerFinded.toString());

        /* If Customer-ItemSet fetch is LAZY the collections is not loaded and...
        customerFinded.getItemSet().forEach(e -> log.info("{}", e.toString() ));
        error will be: failed to lazily initialize a collection of role: com.bext.manytomanybidirtable.entity.Customer.itemSet, could not initialize proxy - no Session
        Then EAGER solve the issue but not recomended. @Transactional one way to solve the issue.
        */
        //customerFinded.getItemSet().forEach(e -> log.info("{}", e.toString() ));


        Customer customer2 = new Customer( "Victoria");
        Customer customer3 = new Customer( "Alba");
        Item itemX = new Item("Item-X");
        itemX.addCustomer(_customer1);
        // customerFinded.addItem(itemX);
        itemX.addCustomer(customer2);
        itemX.addCustomer(customer3);
        customerRepository.save(_customer1);
        //itemRepository.save(itemX);
        //customerRepository.save(customer2);

         /*Customer victoria = customerRepository.myFindByName("Victoria");
         log.info("getCustomerFindByName {}", victoria);*/
         Customer victoria = customerDao.getCustomerFindByName("Victoria");
         log.info("getCustomerFindByName {}", victoria);
     }

    @Transactional
    public void insertCustomerItemsthenFindItem(){
        customerRepository.deleteAll();
        // add customer1 & items
        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        customer1.addItem(itemA);
        Item itemB = new Item("Item-B");
        customer1.addItem(itemB);
        Item itemC = new Item("Item-C");
        customer1.addItem(itemC);
        Item itemD = new Item("Item-D");
        customer1.addItem(itemD);
        customer1.getItemSet().forEach(e -> log.info("item: {}", e.toString() ));

        Customer victoria = new Customer("Victoria");
        victoria.addItem(itemD);
        customerRepository.save(victoria);

        Customer _customer1 = customerRepository.save(customer1);
        log.info("_customer1 {}", _customer1.toString());

        //Item _item = itemRepository.findById(itemD.getId()).get();
        Item _item = itemRepository.findItemsById_named(itemD.getId());      //USES QUERY JOIN FETCH to load customerSet (EAGER)
        //Hibernate.initialize(_item.getCustomerSet());
        log.info("_item:{}", _item);
        log.info("----{}",_item.getCustomerSet().getClass().getName());
        Set<Customer> customerSet = _item.getCustomerSet();

        if ( customerSet instanceof HibernateProxy) {
            log.info("YES instance of HibernateProxy.class");
        } else {
            log.info("NO instance of HibernateProxy.class");
        };

        customerSet.forEach(c -> log.info("customer {}", c.getName()));
    }

    @Transactional
    public void insertCustomerItemsManyTimes(){
        customerRepository.deleteAll();
        // add customer1 & itemA
        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        customer1.addItem(itemA);
        Customer _customer1 = customerRepository.save(customer1);

        Item itemB = new Item("Item-B");
        _customer1.addItem(itemB);
        _customer1 = customerRepository.save(customer1);
        Item itemC = new Item("Item-C");
        _customer1.addItem(itemC);
        _customer1 = customerRepository.save(customer1);
        Item itemD = new Item("Item-D");
        _customer1.addItem(itemD);
        _customer1 = customerRepository.save(customer1);
        customer1.getItemSet().forEach(e -> log.info("item: {}", e.toString() ));

        log.info("_customer1 {}", _customer1.toString());

        Customer customerFinded = customerRepository.findById(_customer1.getId()).get();
        log.info("customerRecovered: {}", customerFinded.toString());
        //customerFinded.getItemSet().forEach(e -> log.info("item: {}", e.toString() ));
    }

    @Transactional
    public void setCustomerRepository_findCustomersByItemSetId(){
        customerRepository.deleteAll();
        // add customer1 & items
        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        customer1.addItem(itemA);
        Item itemB = new Item("Item-B");
        customer1.addItem(itemB);
        Item itemC = new Item("Item-C");
        customer1.addItem(itemC);
        Item itemD = new Item("Item-D");
        customer1.addItem(itemD);
        customer1.getItemSet().forEach(e -> log.info("item: {}", e.toString() ));  //Items without Id yet

        Customer victoria = new Customer("Victoria");
        victoria.addItem(itemD);
        customerRepository.save(victoria);

        Customer _customer1 = customerRepository.save(customer1);
        log.info("_customer1 {}", _customer1.toString());
        _customer1.getItemSet().forEach(e -> log.info("item: {}", e.toString() ));   //Items with Id now.

        List<Customer> customersByItemSetId = customerRepository.findCustomersByItemSetId(1L);
        log.info("customersByItemSetId {}", customersByItemSetId);
    }

    @Transactional
    public void customerDaoTest(){
        customerRepository.deleteAll();
        // add customer1 & itemS
        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        customer1.addItem(itemA);
        Item itemB = new Item("Item-B");
        customer1.addItem(itemB);
        Item itemC = new Item("Item-C");
        customer1.addItem(itemC);
        Item itemD = new Item("Item-D");
        customer1.addItem(itemD);
        Customer _customer1 = customerRepository.save(customer1);
        log.info("_customer1 {}", _customer1.toString());
        customer1.getItemSet().forEach(e -> log.info("item: {}", e.toString() ));

        Customer victoria = new Customer( "Victoria");
        Customer alba = new Customer( "Alba");
        Item itemX = new Item("Item-X");
        victoria.addItem(itemX);
        alba.addItem(itemX);
        customerRepository.save(victoria);
        customerRepository.save(alba);

        Customer _victoria = customerDao.getCustomerFindByName(victoria.getName());
        log.info("_victoria {}", _victoria);
        _victoria.getItemSet().forEach(e -> log.info("item: {}", e.toString()));

        List<CustomerItem> joinInformation = customerRepository.getJoinInformation();
        joinInformation.forEach(e -> log.info("joinInformation: {}", e.toString()));
    }

    @Transactional
    public void namedQuery(){
        loadData();

        Customer customer1 = customerRepository.myFindByName("Jose Alberto");
        log.info("myFindByNameed: {}", customer1);
    }
}
