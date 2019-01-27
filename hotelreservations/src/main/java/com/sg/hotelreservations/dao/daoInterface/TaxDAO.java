package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.Tax;

import java.util.List;

public interface TaxDAO {

    public Tax create(Tax tax);
    public Tax retrieve(Long id);
    public void update(Tax tax);
    public void delete(Tax tax);
    public List<Tax> retrieveAll(int limit, int offset);
    public List<Tax> retrieveByState(String state);
    public List<Tax> retrieveByType(String taxType);
}
