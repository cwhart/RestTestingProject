package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.AddOn;

import java.util.List;

public interface AddOnDAO {

    public AddOn create(AddOn addOn);
    public AddOn retrieve(Long id);
    public void update(AddOn addOn);
    public void delete(AddOn addOn);
    public List<AddOn> retrieveAll(int limit, int offset);

}
