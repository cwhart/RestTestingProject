package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DvdLibraryDaoTest {

    DvdListDao dao;
    DirectorDao directorDao;

    public DvdLibraryDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("DvdLibraryDao", DvdListDao.class);

        // delete all books
        List<Dvd> dvds = dao.getAllDvds();
        for (Dvd currentDvd : dvds) {
            dao.removeDvd(currentDvd.getDvdId());
        }
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addGetDvd() {
        Dvd dvd = new Dvd();
        dvd.setDvdTitle("The Princess Bride");
        dvd.setDirector(directorDao.getDirectorById(1));
        dvd.setReleaseDate(LocalDate.parse("1985-01-01"));
        dvd.setRating("PG");
        dvd.setNotes("Inconceivable!");

        dao.addDvd(dvd);

        Dvd fromDao = dao.getDvdById(dvd.getDvdId());
        assertEquals(dvd.getDvdTitle(), fromDao.getDvdTitle());
        assertEquals(dvd.getDvdId(), fromDao.getDvdId());
        assertEquals(dvd.getDirector(), fromDao.getDirector());
        assertEquals(dvd.getNotes(), fromDao.getNotes());
        assertEquals(dvd.getRating(), fromDao.getRating());
        assertEquals(dvd.getReleaseDate(), fromDao.getReleaseDate());
    }

    @Test
    public void deleteDvd() {
        Dvd dvd = new Dvd();
        dvd.setDvdTitle("The Princess Bride");
        dvd.setDirector(directorDao.getDirectorById(1));
        dvd.setReleaseDate(LocalDate.parse("1985-01-01"));
        dvd.setRating("PG");
        dvd.setNotes("Inconceivable!");

        dao.addDvd(dvd);

        Dvd fromDao = dao.getDvdById(dvd.getDvdId());
        assertEquals(dvd.getDvdTitle(), fromDao.getDvdTitle());
        assertEquals(dvd.getDvdId(), fromDao.getDvdId());
        assertEquals(dvd.getDirector(), fromDao.getDirector());
        assertEquals(dvd.getNotes(), fromDao.getNotes());
        assertEquals(dvd.getRating(), fromDao.getRating());
        assertEquals(dvd.getReleaseDate(), fromDao.getReleaseDate());
        dao.removeDvd(dvd.getDvdId());
        assertNull(dao.getDvdById(dvd.getDvdId()));
    }

}
