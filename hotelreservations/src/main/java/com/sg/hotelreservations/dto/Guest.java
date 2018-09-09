package com.sg.hotelreservations.dto;

import java.util.Objects;

public class Guest {

    Long id;
    Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(id, guest.id) &&
                Objects.equals(person, guest.person);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, person);
    }
}
