package com.sg.hotelreservations.viewmodels.promo;

public class CreatePromoCommandModel {

    String startDate;
    String endDate;
    Long promoTypeId;
    String promoCodeDescription;
    String rate;
    Long rateId;

    public Long getPromoTypeId() {
        return promoTypeId;
    }

    public void setPromoTypeId(Long promoTypeId) {
        this.promoTypeId = promoTypeId;
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
