package com.sg.hotelreservations.viewmodels.promo;

public class EditPromoCommandModel {

    Long id;
    String startDate;
    String endDate;
    Long promoTypeId;
    String promoCodeDescription;
    String rate;
    Long rateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getPromoTypeId() {
        return promoTypeId;
    }

    public void setPromoTypeId(Long promoTypeId) {
        this.promoTypeId = promoTypeId;
    }

    public String getPromoCodeDescription() {
        return promoCodeDescription;
    }

    public void setPromoCodeDescription(String promoCodeDescription) {
        this.promoCodeDescription = promoCodeDescription;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }
}
