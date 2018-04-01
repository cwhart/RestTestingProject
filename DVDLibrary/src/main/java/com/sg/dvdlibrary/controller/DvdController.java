package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdDao;
import com.sg.dvdlibrary.dao.DvdDaoException;
import com.sg.dvdlibrary.dao.DvdDaoFileImpl;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DvdController {

    DvdDao dao;
    DvdView view;
    UserIO io = new UserIOConsoleImpl();


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        //Throw an exception if menu selection is not between 1 and 6.
        try {

            //If menu selection is 1-5, execute the appropriate option; if 6, then exit.
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        displayAll();
                        break;
                    case 5:
                        displayDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                }
            }
            exitMessage();
        } catch (DvdDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public DvdController (DvdDao dao, DvdView view) {
        this.dao = dao;
        this.view = view;
    }

    //Call the view to prompt for user input
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    //Add a new DVD to the library
    private void createDvd() throws DvdDaoException {
        boolean addAnother = true;

        do {

            view.displayCreateNewDvdBanner();

            Dvd newDvd = view.getNewDvdInfo();

            dao.addDvd(newDvd.getTitle(), newDvd);
            view.displayCreateSuccessBanner();
            addAnother = view.anotherOne();

        } while (addAnother);
    }

    //Call the DAO to get the full library, then display.
    private void displayAll() throws DvdDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.displayAll();
        view.displayDvdList(dvdList);

    }

    private void removeDvd() throws DvdDaoException {

        boolean removeAnother = true;

        do {
            view.removeDvdBanner();
            String dvdToRemove = view.getDvdChoice();
            dao.removeDvd(dvdToRemove);
            removeAnother = view.anotherOne();

        } while (removeAnother);

    }

    private void displayDvd() throws DvdDaoException {
        view.displayDvdBanner();
        String dvdToDisplay = view.getDvdChoice();
        Dvd currentDvd = dao.displayDvd(dvdToDisplay);
        view.displayDvd(currentDvd);

    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void editDvd() throws DvdDaoException {
        boolean editAnother = true;

        do {

            //display edit banner
            view.displayEditBanner();

            //prompt user for item to edit. Method returns a key.
            String dvdNameToEdit = view.getDvdChoice();

            //Pass the String key to the DAO to get the Dvd object
            Dvd dvdToEdit = dao.displayDvd(dvdNameToEdit);

            //Now call the edit method
            view.editDvd(dvdToEdit);

            //Store the updated Dvd in the DAO
            dao.editDvd(dvdToEdit);

            //Prompt if user wants to edit another
            editAnother = view.anotherOne();

        } while (editAnother);

         //Display completion banner
        view.displayEditSuccessBanner();

    }
    //Comment

}
