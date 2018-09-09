package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.AddOnBillDetail;

import java.util.List;

public interface AddOnService {

    public AddOn create(AddOn addOn);
    public AddOn retrieve(Long id);
    public void update(AddOn addOn);
    public void delete(AddOn addOn);
    public List<AddOn> retrieveAll(int limit, int offset);
}
