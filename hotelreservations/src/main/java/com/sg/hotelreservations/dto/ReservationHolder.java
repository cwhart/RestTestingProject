package com.sg.hotelreservations.dto;

import java.util.Objects;

public class ReservationHolder {

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
        ReservationHolder that = (ReservationHolder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, person);
    }
}
