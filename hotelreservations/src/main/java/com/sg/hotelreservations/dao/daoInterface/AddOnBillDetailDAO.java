package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.AddOnBillDetail;

import java.util.List;

public interface AddOnBillDetailDAO {

    public AddOnBillDetail create(AddOnBillDetail addOnBillDetail);
    public AddOnBillDetail retrieve(Long id);
    public void update(AddOnBillDetail addOnBillDetail);
    public void delete(AddOnBillDetail addOnBillDetail);
    public List<AddOnBillDetail> retrieveAll(int limit, int offset);
}
