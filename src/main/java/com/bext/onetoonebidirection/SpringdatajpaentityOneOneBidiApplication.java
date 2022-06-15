package com.bext.onetoonebidirection;

import com.bext.onetoonebidirection.entity.Person;
import com.bext.onetoonebidirection.entity.PersonPicture;
import com.bext.onetoonebidirection.repository.PersonPictureRepository;
import com.bext.onetoonebidirection.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringdatajpaentityOneOneBidiApplication implements CommandLineRunner {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonPictureRepository personPictureRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaentityOneOneBidiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Person person1 = new Person("Jose Alberto");
        PersonPicture personPicture = new PersonPicture("picture of Person ...");
        person1.addPicture(personPicture);
        personRepository.save(person1);

        Person _Person = personRepository.findById(1L).get();
        log.info("_Person: {}", _Person);

        PersonPicture _personPicture1 = personPictureRepository.findById(1L).get();
        log.info("_PersonPicture1: {}", _personPicture1);
    }
}
