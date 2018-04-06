package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public class DvdLibraryServiceLayerImpl implements DvdLibraryServiceLayer {

    DvdDao dao;
    private DvdLibraryAuditDao auditDao;

    public DvdLibraryServiceLayerImpl(DvdDao dao, DvdLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addDvd(Dvd dvd) throws DvdLibraryDuplicateTitleException,
            DvdLibraryDataValidationException, DvdLibraryPersistenceException {

        if(dao.displayDvd(dvd.getTitle()) != null) {
            throw new DvdLibraryDuplicateTitleException(
                    "ERROR: Could not add Dvd. Dvd title " + dvd.getTitle()
                    + " already exists."
            );
        }

        validateDvdData(dvd);

        dao.addDvd(dvd.getTitle(), dvd);

        auditDao.writeAuditEntry(
                "DVD " + dvd.getTitle() + " CREATED.");


    }

    @Override
    public List<Dvd> displayAll() throws DvdLibraryPersistenceException {
        return dao.displayAll();
    }

    @Override
    public Dvd displayDvd(String title) throws DvdLibraryPersistenceException {
        return dao.displayDvd(title);
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException {
        Dvd removedDvd = dao.removeDvd(title);

        auditDao.writeAuditEntry("DVD: " + title + " REMOVED.");
        return removedDvd;
    }

    @Override
    public Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        return dao.editDvd(dvd);
    }

    private void validateDvdData(Dvd dvd) throws DvdLibraryDataValidationException {
        if(dvd.getTitle() == null || dvd.getTitle().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("ERROR: Title is required.");
        }
    }
}
