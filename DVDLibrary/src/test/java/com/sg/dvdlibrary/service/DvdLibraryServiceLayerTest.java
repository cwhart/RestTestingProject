package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDaoStubImpl;
import com.sg.dvdlibrary.dao.DvdLibraryDaoStubImpl;
import com.sg.dvdlibrary.dto.Dvd;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DvdLibraryServiceLayerTest {

    private DvdLibraryServiceLayer service;

    public DvdLibraryServiceLayerTest() {
        DvdDao dao = new DvdLibraryDaoStubImpl();
        DvdLibraryAuditDao auditDao = new DvdLibraryAuditDaoStubImpl();
        service = new DvdLibraryServiceLayerImpl(dao, auditDao);
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAddDvd() throws Exception{
        Dvd dvd = new Dvd("Shrek");
        dvd.setReleaseDate("2001");
        dvd.setMpaaRating("PG");
        dvd.setDirectorName("Unknown");
        dvd.setStudio("Disney");
        dvd.setUserComment("Lots of fun!");

        service.addDvd(dvd);
    }

    @Test
    public void testAddDvdDuplicateTitle() throws Exception {
        Dvd dvd = new Dvd("The Quiet Man");
        dvd.setUserComment("Different.");
        dvd.setStudio("hgshfgs");
        dvd.setDirectorName("Ford");
        dvd.setMpaaRating("PG");
        dvd.setReleaseDate("5738");

        try {
            service.addDvd((dvd));
            fail("Expected DvdLibraryDuplicateTitleException was not thrown.");
        } catch (DvdLibraryDuplicateTitleException e) {
            return;
        }
    }

    public void testAddDvdInvalidTitle() throws Exception {
        Dvd dvd = new Dvd(null);
        dvd.setUserComment("Different.");
        dvd.setStudio("hgshfgs");
        dvd.setDirectorName("Ford");
        dvd.setMpaaRating("PG");
        dvd.setReleaseDate("5738");

        try {
            service.addDvd(dvd);
            fail("Expected DvdLibraryDataValidationException was not thrown.");
        } catch (DvdLibraryDataValidationException e) {
            return;
        }
    }

    @Test
    public void displayAll() throws Exception{
        assertEquals(1, service.displayAll().size());
    }

    @Test
    public void displayDvd() throws Exception{
        Dvd dvd = service.displayDvd("The Quiet Man");
        assertNotNull(dvd);

    }

    @Test
    public void removeDvd() throws Exception {
        Dvd dvd = service.removeDvd("The Quiet Man");
        assertNotNull(dvd);
        dvd = service.removeDvd("Monty Python and the Holy Grail");
        assertNull(dvd);

    }

    @Test
    public void editDvd() throws Exception{

        Dvd dvd2 = new Dvd("The Quiet Man");
        dvd2.setUserComment("Different.");
        dvd2.setStudio("hgshfgs");
        dvd2.setDirectorName("Ford");
        dvd2.setMpaaRating("PG");
        dvd2.setReleaseDate("5738");

        service.editDvd(dvd2);

        Dvd editedDvd = service.displayDvd("The Quiet Man");

        assertNotNull(editedDvd);
        assertEquals(dvd2.getStudio(), editedDvd.getStudio());
    }
}