package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.Bill;

import java.util.List;

public interface BillDAO {

    public Bill create(Bill bill);
    public Bill retrieve(Long id);
    public void update(Bill bill);
    public void delete(Bill bill);
    public List<Bill> retrieveAll(int limit, int offset);

    Bill retrieveByReservationId(Long reservationId);
}
