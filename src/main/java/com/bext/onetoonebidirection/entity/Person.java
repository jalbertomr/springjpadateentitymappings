package com.bext.onetoonebidirection.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "person", schema = "public")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "public.person_seq")
    private Long id;
    private String name;
    @OneToOne(mappedBy="person", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private PersonPicture personPicture;

    public Person(String name) {
        this.name = name;
    }

    public void addPicture(PersonPicture personPicture){
        this.setPersonPicture(personPicture);
        personPicture.setPerson(this);
    }

    public void removePicture(){
        this.getPersonPicture().setPerson(null);
        this.setPersonPicture(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonPicture getPersonPicture() {
        return personPicture;
    }

    public void setPersonPicture(PersonPicture personPicture) {
        this.personPicture = personPicture;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personPicture=" + ((personPicture == null) ? "null" : personPicture.getData()) +
                //personPicture.getId() + " " + personPicture.getData() +
                //                     " Person:  " + personPicture.getPerson().getName() +
                '}';
    }
}
