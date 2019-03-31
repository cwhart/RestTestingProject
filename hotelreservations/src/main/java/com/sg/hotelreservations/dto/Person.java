package com.sg.hotelreservations.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Person {

    Long id;
    String firstName;
    String lastName;
    String dateOfBirth;
    String phoneNo;
    String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(dateOfBirth, person.dateOfBirth) &&
                Objects.equals(phoneNo, person.phoneNo) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, dateOfBirth, phoneNo, email);
    }
}
