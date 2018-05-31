package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DvdListDaoTest {

    private DvdListDao dao;
    private DirectorDao directorDao;

    public DvdListDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdListDao", DvdListDao.class);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addGetDeleteDvd() {
        //Add a new Dvd
        Dvd newDvd = new Dvd();
        newDvd.setDvdTitle("Finding Nemo");
        newDvd.setDirector(directorDao.getDirectorById(1));
        newDvd.setNotes("Shark bait!");
        newDvd.setRating("G");
        newDvd.setReleaseDate(LocalDate.parse("2005/01/01"));
        dao.addDvd(newDvd);

        Dvd fromDb = dao.getDvdById(newDvd.getDvdId());
        assertEquals(fromDb, newDvd);
        dao.removeDvd(newDvd.getDvdId());
        assertNull(dao.getDvdById(newDvd.getDvdId()));

    }

    @Test
    public void addUpdateDvd() {
        Dvd newDvd = new Dvd();
        newDvd.setDvdTitle("Finding Nemo");
        newDvd.setDirector(directorDao.getDirectorById(1));
        newDvd.setNotes("Shark bait!");
        newDvd.setRating("G");
        newDvd.setReleaseDate(LocalDate.parse("2005/01/01"));
        dao.addDvd(newDvd);

        newDvd.setDirector(directorDao.getDirectorById(2));
        dao.updateDvd(newDvd);
        Dvd fromDb = dao.getDvdById(newDvd.getDvdId());
        assertEquals(fromDb, newDvd);
    }

    @Test
    public void getAllDvds() {
        Dvd newDvd = new Dvd();
        newDvd.setDvdTitle("Finding Nemo");
        newDvd.setDirector(directorDao.getDirectorById(1));
        newDvd.setNotes("Shark bait!");
        newDvd.setRating("G");
        newDvd.setReleaseDate(LocalDate.parse("2005/01/01"));
        dao.addDvd(newDvd);

        Dvd newDvd2 = new Dvd();
        newDvd2.setDvdTitle("E.T.");
        newDvd2.setDirector(directorDao.getDirectorById(1));
        newDvd2.setNotes("ET phone home");
        newDvd2.setRating("PG");
        newDvd2.setReleaseDate(LocalDate.parse("2005/01/01"));
        dao.addDvd(newDvd2);

        List<Dvd> dvdList = dao.getAllDvds();
        assertEquals(dvdList.size(), 2);
    }

    @Test
    public void searchDvds() {
        Dvd newDvd = new Dvd();
        newDvd.setDvdTitle("Finding Nemo");
        newDvd.setDirector(directorDao.getDirectorById(1));
        newDvd.setNotes("Shark bait!");
        newDvd.setRating("G");
        newDvd.setReleaseDate(LocalDate.parse("2005/01/01"));
        dao.addDvd(newDvd);

        Dvd newDvd2 = new Dvd();
        newDvd2.setDvdTitle("Spinal Tap");
        newDvd2.setDirector(directorDao.getDirectorById(1));
        newDvd2.setNotes("Turn it up to 11.");
        newDvd2.setRating("PG");
        newDvd2.setReleaseDate(LocalDate.parse("1983/01/01"));
        dao.addDvd(newDvd2);

        Dvd newDvd3 = new Dvd();
        newDvd3.setDvdTitle("The Princess Bride");
        newDvd3.setDirector(directorDao.getDirectorById(1));
        newDvd3.setNotes("Inconceivable!");
        newDvd3.setRating("PG");
        newDvd.setReleaseDate(LocalDate.parse("1985/01/01"));
        dao.addDvd(newDvd3);

        //Map<SearchTerm, String> criteria = new HashMap<>();
        //criteria.put(SearchTerm.DVD_DIRECTOR, "Reiner");
        //List<Dvd> dvdList = dao.searchDvds(criteria);
        //assertEquals(dvdList.size(), 2);


        //criteria.put(SearchTerm.DVD_DIRECTOR, "Stanton");
        //dvdList = dao.searchDvds(criteria);
        //assertEquals(1, dvdList.size());
        //assertEquals(newDvd, dvdList.get(0));
    }
}
