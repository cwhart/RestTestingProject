package com.sg.library.model;

import java.util.Objects;

public class Publisher {

    private int publisherId;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return publisherId == publisher.publisherId &&
                Objects.equals(name, publisher.name) &&
                Objects.equals(street, publisher.street) &&
                Objects.equals(city, publisher.city) &&
                Objects.equals(state, publisher.state) &&
                Objects.equals(zip, publisher.zip) &&
                Objects.equals(phone, publisher.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(publisherId, name, street, city, state, zip, phone);
    }
}
