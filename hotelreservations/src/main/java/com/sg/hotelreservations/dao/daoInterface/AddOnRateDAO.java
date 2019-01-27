package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.AddOnRate;

import java.time.LocalDate;
import java.util.List;

public interface AddOnRateDAO {

    public AddOnRate create(AddOnRate addOnRate);
    public AddOnRate retrieve(Long id);
    public void update(AddOnRate addOnRate);
    public void delete(AddOnRate addOnRate);
    public List<AddOnRate> retrieveAll(int limit, int offset);
    public List<AddOnRate> retrieveByAddOnId(Long addOnId);
}
