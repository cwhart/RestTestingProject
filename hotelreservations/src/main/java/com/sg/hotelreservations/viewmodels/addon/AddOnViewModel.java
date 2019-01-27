package com.sg.hotelreservations.viewmodels.addon;

import com.sg.hotelreservations.dto.Amenity;

import java.math.BigDecimal;
import java.util.List;

public class AddOnViewModel {

    private Long id;
    private String type;
    private BigDecimal rate;

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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
