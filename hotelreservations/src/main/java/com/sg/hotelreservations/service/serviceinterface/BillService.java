package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Bill;

import java.math.BigDecimal;
import java.util.List;

public interface BillService {

    public Bill create(Bill bill);
    public Bill retrieve(Long id);
    public void update(Bill bill);
    public void delete(Bill bill);
    public List<Bill> retrieveAll(int limit, int offset);

    Bill retrieveByReservationId(Long reservationId);
    public BigDecimal updateBillTotal(Bill bill);
}
