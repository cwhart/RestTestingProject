package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DvdLibraryDaoStubImpl implements DvdDao {

    private Dvd onlyDvd;
    private List<Dvd> dvdList = new ArrayList<>();

    public DvdLibraryDaoStubImpl() {
        onlyDvd = new Dvd("The Quiet Man");
        onlyDvd.setUserComment("My husband has an odd obsession with this movie.");
        onlyDvd.setStudio("MGM");
        onlyDvd.setDirectorName("John Ford");
        onlyDvd.setMpaaRating("PG");
        onlyDvd.setReleaseDate(LocalDate.parse("1950-01-01"));

        dvdList.add(onlyDvd);
    }

    @Override
    public Dvd addDvd(String title, Dvd newDvd) throws DvdLibraryPersistenceException {
        if (title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException {
        if (title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editDvd(Dvd dvdToEdit) throws DvdLibraryPersistenceException {
        if (dvdToEdit.getTitle().equals(onlyDvd.getTitle())) {
            onlyDvd = dvdToEdit;
            return dvdToEdit;
        } else {
            return null;
        }
    }

    @Override
    public List<Dvd> displayAll() throws DvdLibraryPersistenceException {
        return dvdList;
    }

    @Override
    public Dvd displayDvd(String title) throws DvdLibraryPersistenceException {
        if (title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        } else {
            return null;
        }


    }

    @Override
    public List<Dvd> findMoviesReleasedInPastNYears(int numberOfYears) {
        return null;
    }

    @Override
    public List<Dvd> findMoviesWithSpecifiedRating(String rating) {
        return null;
    }

    @Override
    public List<Dvd> findMoviesWithSpecifiedDirector(String director) {
        return null;
    }

    @Override
    public List<Dvd> findMoviesBySpecifiedStudio(String studio) {
        return null;
    }

    @Override
    public double findAverageAge() {
        return 0;
    }

    @Override
    public Dvd findNewestMovie() {
        return null;
    }

    @Override
    public Dvd findOldestMovie() {
        return null;
    }

    @Override
    public int findAverageNumberOfNotes() {
        return 0;
    }
}
