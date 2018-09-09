package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.AddOnRate;

import java.util.List;

public interface AddOnRateService {

    public AddOnRate create(AddOnRate addOnRate);
    public AddOnRate retrieve(Long id);
    public void update(AddOnRate addOnRate);
    public void delete(AddOnRate addOnRate);
    public List<AddOnRate> retrieveAll(int limit, int offset);
}
