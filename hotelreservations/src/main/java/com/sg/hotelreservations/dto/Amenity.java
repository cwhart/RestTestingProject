package com.sg.hotelreservations.dto;

import java.util.Objects;

public class Amenity {

    Long id;
    String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenity amenity = (Amenity) o;
        return id == amenity.id &&
                Objects.equals(type, amenity.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type);
    }
}
