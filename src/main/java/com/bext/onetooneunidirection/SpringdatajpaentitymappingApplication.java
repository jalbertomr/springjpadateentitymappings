package com.bext.onetooneunidirection;

import com.bext.onetooneunidirection.dto.CountryCapital;
import com.bext.onetooneunidirection.entity.Country;
import com.bext.onetooneunidirection.entity.Capital;
import com.bext.onetooneunidirection.repository.CapitalRepository;
import com.bext.onetooneunidirection.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringdatajpaentitymappingApplication implements CommandLineRunner {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CapitalRepository capitalRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaentitymappingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /* @OneToOne Unidirection
        class Country {                                      create table dummy.country (
            long   id;                                         id         bigint not null,
            String name;                                       name       varchar(255),
            @OneToOne
            Capital   capital;                                       capital_id_fk bigint,
                                                                          primary key (id)
         )
         class Capital {                                          create table dummy.capital (
            long id;                                            id         bigint not null,
            String name;                                        name       varchar(255),
         }                                                      primary key (id)
                                                                )
         given  new Country , new Capital ->  country set [capital] -> save Country
         then   capital saved
         test   recoverCapital = capital not directly saved

        */
        Country country = new Country("Mexico");
        Capital capital = new Capital("CDMX");
        country.setCapital(capital);
        Country _country = countryRepository.save(country);
        log.info("savedCountry {}", _country);

        Country countryFinded = countryRepository.findById(_country.getId()).get();
        log.info("recoveredCountry: {}", countryFinded);

        Capital recoveredCapital = capitalRepository.findById(_country.getCapital().getId()).get();
        log.info("recoveredCapital: {}", recoveredCapital);

        // given new Country , new Capital  -> capital set country IMPOSSIBLE -> save capital
        // never save Country

        List<CountryCapital> countryCapitalList = countryRepository.getJoinCountryCapital();
        countryCapitalList.forEach(e -> log.info("countryCapital: {}", e));
    }
}
