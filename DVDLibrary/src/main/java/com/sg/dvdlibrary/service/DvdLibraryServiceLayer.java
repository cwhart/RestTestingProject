package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;
import java.util.Map;

public interface DvdLibraryServiceLayer {

    void addDvd (Dvd dvd) throws DvdLibraryDuplicateTitleException,
                        DvdLibraryDataValidationException,
                        DvdLibraryPersistenceException;

    List<Dvd> displayAll() throws DvdLibraryPersistenceException;

    Dvd displayDvd(String title) throws DvdLibraryPersistenceException;

    Dvd removeDvd(String title) throws DvdLibraryPersistenceException;

    Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException;

    public List<Dvd> retrieveMoviesWithGivenRating(String rating) throws DvdLibraryPersistenceException;

    public List<Dvd> retrieveMoviesReleasedInNYears(int numYears) throws DvdLibraryPersistenceException;

    public List<Dvd> retrieveMoviesWithGivenDirector(String director) throws DvdLibraryPersistenceException;

    public List<Dvd> retrieveMoviesWithGivenStudio(String studio) throws DvdLibraryPersistenceException;

    public Dvd retrieveNewestMovie() throws DvdLibraryPersistenceException;

    public Dvd retrieveOldestMovie() throws DvdLibraryPersistenceException;

    public double retrieveAverageAge() throws DvdLibraryPersistenceException;

}
