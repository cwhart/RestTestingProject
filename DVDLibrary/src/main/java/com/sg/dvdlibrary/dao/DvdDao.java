package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdDao {

    //DVD DAO interface

    public Dvd addDvd(String title, Dvd newDvd) throws DvdDaoException;

    public Dvd removeDvd(String title) throws DvdDaoException;

    public Dvd editDvd(Dvd dvdToEdit) throws DvdDaoException;

    public List<Dvd> displayAll() throws DvdDaoException;

    public Dvd displayDvd(String title) throws DvdDaoException;

    //Comment
}
