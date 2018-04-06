package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.service.DvdLibraryDataValidationException;
import com.sg.dvdlibrary.service.DvdLibraryDuplicateTitleException;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DvdView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DvdController {

    DvdLibraryServiceLayer service;
    DvdView view;
    //UserIO io = new UserIOConsoleImpl();


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
        } catch (DvdLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public DvdController (DvdLibraryServiceLayer service, DvdView view) {
        this.service = service;
        this.view = view;
    }

    //Call the view to prompt for user input
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    //Add a new DVD to the library
    private void createDvd() throws DvdLibraryPersistenceException {
        boolean addAnother = true;
        boolean hasErrors = false;

        do {

            view.displayCreateNewDvdBanner();

            Dvd newDvd = view.getNewDvdInfo();

            try {

                service.addDvd(newDvd);
                view.displayCreateSuccessBanner();
                addAnother = view.anotherOne();
                hasErrors = false;
            } catch (DvdLibraryDuplicateTitleException | DvdLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            } while (hasErrors);

        } while (addAnother);

    }


    //Call the DAO to get the full library, then display.
    private void displayAll() throws DvdLibraryPersistenceException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = service.displayAll();
        view.displayDvdList(dvdList);

    }

    private void removeDvd() throws DvdLibraryPersistenceException {

        boolean removeAnother = true;

        do {
            view.removeDvdBanner();
            String dvdToRemove = view.getDvdChoice();
            service.removeDvd(dvdToRemove);
            removeAnother = view.anotherOne();

        } while (removeAnother);

    }

    private void displayDvd() throws DvdLibraryPersistenceException {
        view.displayDvdBanner();
        String dvdToDisplay = view.getDvdChoice();
        Dvd currentDvd = service.displayDvd(dvdToDisplay);
        view.displayDvd(currentDvd);

    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void editDvd() throws DvdLibraryPersistenceException {
        boolean editAnother = true;

        do {

            //display edit banner
            view.displayEditBanner();

            //prompt user for item to edit. Method returns a key.
            String dvdNameToEdit = view.getDvdChoice();

            //Pass the String key to the DAO to get the Dvd object
            Dvd dvdToEdit = service.displayDvd(dvdNameToEdit);

            //Now call the edit method
            view.editDvd(dvdToEdit);

            //Store the updated Dvd in the DAO
            service.editDvd(dvdToEdit);

            //Prompt if user wants to edit another
            editAnother = view.anotherOne();

        } while (editAnother);

         //Display completion banner
        view.displayEditSuccessBanner();

    }
    //Comment

}
