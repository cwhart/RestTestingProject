package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryServiceLayer {

    void addDvd (Dvd dvd) throws DvdLibraryDuplicateTitleException,
                        DvdLibraryDataValidationException,
                        DvdLibraryPersistenceException;

    List<Dvd> displayAll() throws DvdLibraryPersistenceException;

    Dvd displayDvd(String title) throws DvdLibraryPersistenceException;

    Dvd removeDvd(String title) throws DvdLibraryPersistenceException;

    Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException;

}
