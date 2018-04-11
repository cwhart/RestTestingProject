package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DvdDao {

    //DVD DAO interface

    public Dvd addDvd(String title, Dvd newDvd) throws DvdLibraryPersistenceException;

    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException;

    public Dvd editDvd(Dvd dvdToEdit) throws DvdLibraryPersistenceException;

    public List<Dvd> displayAll() throws DvdLibraryPersistenceException;

    public Dvd displayDvd(String title) throws DvdLibraryPersistenceException;

   public List<Dvd> findMoviesReleasedInPastNYears(int numberOfYears) throws DvdLibraryPersistenceException;

    public List<Dvd> findMoviesWithSpecifiedRating(String rating) throws DvdLibraryPersistenceException;

    public List<Dvd> findMoviesWithSpecifiedDirector(String director) throws DvdLibraryPersistenceException;

    public List<Dvd> findMoviesBySpecifiedStudio (String studio) throws DvdLibraryPersistenceException;

    public double findAverageAge() throws DvdLibraryPersistenceException;

    public Dvd findNewestMovie() throws DvdLibraryPersistenceException;

    public Dvd findOldestMovie() throws DvdLibraryPersistenceException;

    public int findAverageNumberOfNotes() throws DvdLibraryPersistenceException;



    //Comment
}
