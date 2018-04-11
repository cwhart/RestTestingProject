package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DvdDaoTest {

    DvdDao dao = new DvdDaoFileImpl();

    @Before
    public void setUp() throws Exception {
        List<Dvd> dvdList = dao.displayAll();
        for (Dvd dvd : dvdList) {
            dao.removeDvd(dvd.getTitle());
        }
    }

    @Test
    public void testAddGetDvd() throws Exception {
        Dvd dvd = new Dvd("Cars");
        dvd.setReleaseDate(LocalDate.parse("2006-01-01"));
        dvd.setMpaaRating("G");
        dvd.setDirectorName("Unknown");
        dvd.setStudio("Pixar");
        dvd.setUserComment("My dad's favorite.");

        dao.addDvd(dvd.getTitle(), dvd);

        Dvd fromDao = dao.displayDvd(dvd.getTitle());

        assertEquals(dvd, fromDao);
    }

    @Test
    public void removeDvd() throws Exception{

        Dvd dvd1 = new Dvd("Dances With Wolves");
        dvd1.setReleaseDate(LocalDate.parse("1990-01-01"));
        dvd1.setMpaaRating("PG13");
        dvd1.setDirectorName("Director");
        dvd1.setStudio("Paramount");
        dvd1.setUserComment("These are my comments");
        dao.addDvd(dvd1.getTitle(), dvd1);

        Dvd dvd2 = new Dvd("Spartacus");
        dvd2.setReleaseDate(LocalDate.parse("1960-01-01"));
        dvd2.setMpaaRating("R");
        dvd2.setDirectorName("Director2");
        dvd2.setStudio("Unknown");
        dvd2.setUserComment("These are my other comments");
        dao.addDvd(dvd2.getTitle(), dvd2);

        dao.removeDvd("Dances With Wolves");
        assertEquals(1, dao.displayAll().size());
        assertNull((dao.displayDvd("Dances With Wolves")));

        dao.removeDvd("Spartacus");
        assertEquals(0, dao.displayAll().size());
        assertNull(dao.displayDvd("Spartacus"));
    }

    @Test
    public void editDvd() throws Exception {

        Dvd dvd1 = new Dvd("Dances With Wolves");
        dvd1.setReleaseDate(LocalDate.parse("1990-01-01"));
        dvd1.setMpaaRating("PG13");
        dvd1.setDirectorName("Director");
        dvd1.setStudio("Paramount");
        dvd1.setUserComment("These are my comments");
        dao.addDvd(dvd1.getTitle(), dvd1);

        Dvd dvd2 = new Dvd("Dances With Wolves");
        dvd2.setReleaseDate(LocalDate.parse("2001-01-01"));
        dvd2.setMpaaRating("R");
        dvd2.setDirectorName("Spielberg");
        dvd2.setStudio("MGM");
        dvd2.setUserComment("Updated comments.");

        dao.editDvd(dvd2);

        Dvd updatedDvd = dao.displayDvd("Dances With Wolves");

        assertEquals(dvd2.getTitle(), updatedDvd.getTitle());
        assertEquals(dvd2.getDirectorName(), updatedDvd.getDirectorName());
        assertEquals(dvd2.getMpaaRating(), updatedDvd.getMpaaRating());
        assertEquals(dvd2.getReleaseDate(), updatedDvd.getReleaseDate());
        assertEquals(dvd2.getStudio(), updatedDvd.getStudio());
        assertEquals(dvd2.getUserComment(), updatedDvd.getUserComment());
    }

    @Test
    public void displayAll() throws Exception{

        Dvd dvd1 = new Dvd("Dances With Wolves");
        dvd1.setReleaseDate(LocalDate.parse("1990-01-01"));
        dvd1.setMpaaRating("PG13");
        dvd1.setDirectorName("Director");
        dvd1.setStudio("Paramount");
        dvd1.setUserComment("These are my comments");

        dao.addDvd(dvd1.getTitle(), dvd1);

        Dvd dvd2 = new Dvd("Spartacus");
        dvd2.setReleaseDate(LocalDate.parse("1960-01-01"));
        dvd2.setMpaaRating("R");
        dvd2.setDirectorName("Director2");
        dvd2.setStudio("Unknown");
        dvd2.setUserComment("These are my other comments");

        dao.addDvd(dvd2.getTitle(), dvd2);

        assertEquals(2, dao.displayAll().size());
    }



}