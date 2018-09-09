package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;

import java.util.List;

public interface AddOnBillDetailService {

    public AddOnBillDetail create(AddOnBillDetail addOnBillDetail);
    public AddOnBillDetail retrieve(Long id);
    public void update(AddOnBillDetail addOnBillDetail);
    public void delete(AddOnBillDetail addOnBillDetail);
    public List<AddOnBillDetail> retrieveAll(int limit, int offset);
}
