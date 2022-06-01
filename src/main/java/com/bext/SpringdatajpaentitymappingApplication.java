package com.bext;

import com.bext.entity.Customer;
import com.bext.entity.Item;
import com.bext.repository.ItemRepository;
import com.bext.repository.CustomerRepository;
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
        Customer customer = new Customer("Jose Alberto");
        Item item = new Item("Item2");
        customer.setItem( item);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("savedCustomer {}", savedCustomer);
    }
}
