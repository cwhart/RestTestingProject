package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;

import java.util.List;

public interface ChangeDao {

    public List<Change> retrieveAllChange()  ;

    public void createChange(Change change)  ;

    public void removeChange(int changeNum) ;

    public void updateChange(Change change) ;

    public Change retrieveChange(int changeNo) ;
}
