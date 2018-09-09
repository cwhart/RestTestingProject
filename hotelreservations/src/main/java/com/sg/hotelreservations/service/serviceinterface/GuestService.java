package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Guest;

import java.util.List;

public interface GuestService {

    public Guest create(Guest guest);
    public Guest retrieve(Long id);
    public void update(Guest guest);
    public void delete(Guest guest);
    public List<Guest> retrieveAll(int limit, int offset);
}
