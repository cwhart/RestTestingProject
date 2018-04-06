package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdDao {

    //DVD DAO interface

    public Dvd addDvd(String title, Dvd newDvd) throws DvdLibraryPersistenceException;

    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException;

    public Dvd editDvd(Dvd dvdToEdit) throws DvdLibraryPersistenceException;

    public List<Dvd> displayAll() throws DvdLibraryPersistenceException;

    public Dvd displayDvd(String title) throws DvdLibraryPersistenceException;

    //Comment
}
