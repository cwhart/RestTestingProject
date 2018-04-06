package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DvdLibraryDaoStubImpl implements DvdDao {

    private Dvd onlyDvd;
    private List<Dvd> dvdList = new ArrayList<>();

    public DvdLibraryDaoStubImpl() {
        onlyDvd = new Dvd("The Quiet Man");
        onlyDvd.setUserComment("My husband has an odd obsession with this movie.");
        onlyDvd.setStudio("MGM");
        onlyDvd.setDirectorName("John Ford");
        onlyDvd.setMpaaRating("PG");
        onlyDvd.setReleaseDate("1950");

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
}
