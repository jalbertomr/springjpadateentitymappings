package com.bext.onetoonebidirection.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "person_picture", schema = "public")
public class PersonPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "public.person_picture_seq")
    private Long id;
    private String data;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public PersonPicture(String data) {
        this.data = data;
    }

    public void addPerson(Person person){
        person.setPersonPicture(this);
        this.setPerson(person);
    }

    public void removePerson(){
        this.getPerson().setPersonPicture(null);
        this.setPerson(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonPicture{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", person=" + ((person == null) ? "null" : person.getId() + " " + person.getName() ) +
                '}';
    }
}
