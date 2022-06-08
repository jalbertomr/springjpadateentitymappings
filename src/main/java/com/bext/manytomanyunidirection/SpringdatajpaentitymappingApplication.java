package com.bext.manytomanyunidirection;

import com.bext.manytomanyunidirection.dto.CustomerItem;
import com.bext.manytomanyunidirection.entity.Customer;
import com.bext.manytomanyunidirection.entity.Item;
import com.bext.manytomanyunidirection.repository.CustomerRepository;
import com.bext.manytomanyunidirection.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringdatajpaentitymappingApplication implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run( SpringdatajpaentitymappingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /* @ManyToMany unidirectional Customer has reference to Item, But Item Don´t to Customer
        This NOT WORK @ManyToMany in Only one side entity DO NOT do a ManyToMany behavior.

        class Customer {                              create table customer (
           Long id;                                            id   int8 not null,
           String name;                                        name varchar(255),
           Set<Item> items = new HashSet<>();                     primary key (id)
        }                                             )

                                                    create table dummy.customer_items (
                                                        customer_id int8 not null,
                                                        items_id    int8 not null,
                                                        primary key (customer_id, items_id)
                                                    )

        class Item {                                create table dummy.item (
           Long id;                                        id    int8 not null,
           String name;                                    name  varchar(255),
        }                                                  primary key (id)
                                                    )
         */

        customersItems();
    }

    @Transactional
    public void customersItems(){
        Customer jose_alberto = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        Item itemB = new Item("Item-B");
        Item itemC = new Item("Item-C");
        jose_alberto.addItem(itemA);
        jose_alberto.addItem(itemB);
        jose_alberto.addItem(itemC);
        Customer _jose_alberto = customerRepository.save(jose_alberto);

        Item _itemA = itemRepository.findByName(itemA.getName());
        log.info("_itemA: {}", _itemA.toString());
        /*_jose_alberto.getItems().forEach(e -> log.info("item: {}", e));
        Item _itemA = _jose_alberto.getItems().stream().filter(item -> item.getName() == itemA.getName()).findFirst().orElse(null);
        */
        Customer victoria = new Customer("Victoria");
        Item itemX = new Item("Item-X");

        //TODO add itemA to victoria
        //victoria.addItem(_itemA);  //detached entity passed to persist: com.bext.manytomanyunidirection.entity.Item
        victoria.addItem(itemX);
        customerRepository.save(victoria);
/*
        Customer alba = new Customer("Alba");
        alba.addItem(itemC);
        customerRepository.save(alba);
*/
        List<CustomerItem> customersItems = customerRepository.getCustomersItems();
        customersItems.forEach(e -> log.info("customersItems: {}", e.toString()));
    }

    public void test1(){
        // add customer1 & itemA
        Customer customer1 = new Customer("Jose Alberto");
        Item itemA = new Item("Item-A");
        customer1.getItems().add(itemA);
//        Customer customerSaved = customerRepository.save(customer1);

        // Customer add ItemB
        //Customer customerRecovered = customerRepository.findById(customerSaved.getId()).get();
        Item itemB = new Item("Item-B");
        customer1.getItems().add(itemB);
        //customerRepository.save(customerRecovered);

        // add ItemC to cuwtomer1, save directly ItemC WIll NOT save Customer.
        //Customer customerRecovered2 = customerRepository.findById(customerSaved.getId()).get();
        Item itemC = new Item("Item-C");
        customer1.getItems().add(itemC);
        //customerRepository.save(customerRecovered2);

        Customer customerSaved = customerRepository.save(customer1);

        List<CustomerItem> customersItems = customerRepository.getCustomersItems();
        customersItems.forEach(e -> log.info("customersitems: {}", e.toString()));
/*

      Error: Is repeating the Item-B
 customer_id | items_id | id |     name     | id |  name
-------------+----------+----+--------------+----+--------
           1 |        1 |  1 | Jose Alberto |  1 | Item-A
           1 |        2 |  1 | Jose Alberto |  2 | Item-B
           1 |        3 |  1 | Jose Alberto |  3 | Item-B
           1 |        4 |  1 | Jose Alberto |  4 | Item-C

 */

/*
        Item itemD = new Item("Item-D");
        customer1.getItems().add(itemD);
        customerRepository.save(customer1);


        Customer customer2 = new Customer("Victoria");
        customer2.getItems().add(new Item("itemD"));
        customerRepository.save(customer2);

        Item itemSaved = itemRepository.save(new Item("Item-Indep"));
        customer1.getItems().add(itemSaved);
        customerRepository.save(customer1);
*/

    }
}
