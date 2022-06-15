package com.bext.onetooneunidirsharedkey;

import com.bext.onetooneunidirsharedkey.entity.Customer;
import com.bext.onetooneunidirsharedkey.entity.Item;
import com.bext.onetooneunidirsharedkey.repository.CustomerRepository;
import com.bext.onetooneunidirsharedkey.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

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
        /* @OneToOne using SharedKey creates well the table structure but don´t
        automatize the cascading, don´t assign the foreign key, is done manually.


         */
        oneToOneSharedKeyIsNotAutomatically();
    }

    @Transactional
    public void oneToOneSharedKeyIsNotAutomatically(){
        //Has to be done manually
        Customer customer = new Customer("Jose Alberto");
        log.info("customer: {}", customer);
        Customer _customer = customerRepository.save(customer);
        log.info("_customer retrieved: {}", _customer);
        Item itemA = new Item("Item-A");
        itemA.setId(_customer.getId());
        itemA.setCustomer(_customer);
        customer.setItem(itemA);
        log.info("itemA {}", itemA);
        log.info("customer: {}", customer);
        customerRepository.save(customer);

    }
}
