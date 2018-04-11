package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.util.List;

public class DvdView {

    private UserIO io;

    public DvdView(UserIO io) {
        this.io = io;
    }

    //Print out main menu and prompt user for selection. Return value is user selection.
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Update DVD information");
        io.print("4. Display all DVD titles");
        io.print("5. Search for a DVD");
        io.print("6. Find all movies released in the last N years");
        io.print("7. Find all movies with a given rating");
        io.print("8. Find all movies with a given director");
        io.print("9. Find all movies released by a given studio");
        io.print("10. Find the average age of movies in the collection");
        io.print("11. Find the newest movie in the collection");
        io.print("12. Find the oldest movie in the collection");
        io.print("13. Find the average number of notes associated with movies in the collection");
        io.print("0. Exit");


        return (io.readInt("Enter your selection: ", 0, 13));
    }

    //Prompt user for DVD attribute values. Create a new Dvd object and return.
    public Dvd getNewDvdInfo() {
        Dvd newDvd = new Dvd(io.readString("Enter the name of the DVD"));
        newDvd.setReleaseDate(io.readDate("Enter the release date of the DVD: "));
        newDvd.setMpaaRating(io.readString("Enter the MPAA rating of the DVD: "));
        newDvd.setDirectorName(io.readString("Enter the director's name: "));
        newDvd.setStudio(io.readString("Enter the studio name: "));
        newDvd.setUserComment(io.readString("Enter a comment: "));

        return newDvd;
    }

    //Generic method to prompt user if they want to do another round - to be used for add, remove and
    //edit functions.
    public boolean anotherOne() {
        String another = io.readString("Another one? Y/N");
        if (another.toLowerCase().equals("y")) {
            return true;
        } return false;
    }

    public void displayCreateNewDvdBanner() {
        io.print("\n=== CREATE NEW DVD ENTRY ===\n");
    }

    public void displayCreateSuccessBanner() {
        io.print("\n=== DVD SUCCESSFULLY ADDED TO LIBRARY ===\n");
    }

    //Display list of all - originally was displaying all DVD info; updated to comment out code so
    //it only displays DVD titles.
    public void displayDvdList(List<Dvd> listOfDvds) {
        for (Dvd currentDvd : listOfDvds) {
            io.print(currentDvd.getTitle());

            //Originally was displaying all fields for each DVD; made update to only display the titles.
            /*io.print("Release Date: " + currentDvd.getReleaseDate());
            io.print("Rating: " + currentDvd.getMpaaRating());
            io.print("Director's Name: " + currentDvd.getDirectorName());
            io.print("Studio: " + currentDvd.getStudio());
            io.print("Comment: " + currentDvd.getUserComment());
            io.print("");*/
        }
        io.print("");

    }

    public void displayDisplayAllBanner() {
        io.print("\n=== DISPLAY ALL DVDs IN LIBRARY ===\n");
    }

    public void removeDvdBanner() {
        io.print("\n=== REMOVE A DVD ===\n");
    }

    public void displayRemoveSuccessBanner() {
        io.print("\n=== DVD REMOVED SUCCESSFULLY ===\n");
    }

    //Prompt user to enter a DVD title - to be used for remove, edit and search.
    public String getDvdChoice() {
        return io.readString("Please select a DVD: ");

    }

    public void displayDvdBanner() {
        io.print("\n=== DISPLAY A DVD ===\n");
    }

    public void displayDvd(Dvd dvdToDisplay) {
        io.print(dvdToDisplay.getTitle() + ":");
        io.print("Release Date: " + dvdToDisplay.getReleaseDate());
        io.print("Rating: " + dvdToDisplay.getMpaaRating());
        io.print("Director's Name: " + dvdToDisplay.getDirectorName());
        io.print("Studio: " + dvdToDisplay.getStudio());
        io.print("Comment: " + dvdToDisplay.getUserComment());
        io.print("");
    }

    public void displayExitBanner() {
        io.print("\n===GOOD-BYE===\n");
    }

    public void displayUnknownCommandBanner() {
        io.print("\n===UNKNOWN COMMAND===\n");
    }

    public void displayErrorMessage(String error) {
        io.print(error);
    }

    public void displayEditBanner() {
        io.print("\n=== EDIT A DVD ===\n");
    }

    //Takes in DVD object, then goes through field by field to
    //display the current value and prompt the user to update or
    //hit enter to continue. If string entered is not null, call
    //the setter to update the value.
    //User cannot update the DVD title, as this is the key.
    public Dvd editDvd(Dvd dvdToEdit) {

        //Go through the properties and prompt the user to edit

        //Release Date
        io.print("Current release date: " + dvdToEdit.getReleaseDate());
        String newReleaseDate = io.readString("Please enter new release date, " +
                "or hit <enter> to continue without editing.");

        if(!newReleaseDate.equals("")) {
            dvdToEdit.setReleaseDate(LocalDate.parse(newReleaseDate));
        }

        //MPAA Rating
        io.print("Current MPAA Rating: " + dvdToEdit.getMpaaRating());
        String newMpaaRating = io.readString("Please enter new MPAA rating, " +
                "or hit <enter> to continue without editing.");

        if(!newMpaaRating.equals("")) {
            dvdToEdit.setMpaaRating(newMpaaRating);
        }

        //Director's Name
        io.print("Current Director's name: " + dvdToEdit.getDirectorName());
        String newDirectorsName = io.readString("Please enter new Director's name, " +
                "or hit <enter> to continue without editing.");

        if(!newDirectorsName.equals("")) {
            dvdToEdit.setDirectorName(newDirectorsName);
        }

        //Studio Name
        io.print("Current Studio name: " + dvdToEdit.getStudio());
        String newStudioName = io.readString("Please enter new Studio name, " +
                "or hit <enter> to continue without editing.");

        if(!newStudioName.equals("")) {
            dvdToEdit.setStudio(newStudioName);
        }
        //User Comment
        io.print("Current user comment: " + dvdToEdit.getUserComment());
        String newUserComment = io.readString("Please enter new user comment, " +
                "or hit <enter> to continue without editing.");

        if(!newUserComment.equals("")) {
            dvdToEdit.setUserComment(newUserComment);
        }

        return dvdToEdit;
    }

    public void displayEditSuccessBanner() {
        io.print("\n=== DVD EDITED SUCCESSFULLY ===\n");
    }

    public void displayUnknownDvdTitleMessage() {
        io.print("Unknown DVD Title!");
    }

    public String retrieveRatingSelection() {
        return io.readString("Please enter the rating you want to search on.");
    }

    public int retrieveNumberOfYears() {
        return io.readInt("Please enter the number of years.");
    }

    public String retrieveDirectorSelection() {
        return io.readString("Please enter the name of the director");
    }

    public String retrieveStudioName() {
        return io.readString("Please enter the studio name.");
    }

    public void displayAverageAgeofMovies (double averageAge) {
        io.print("Average age of movies is: " + averageAge + "years.");
    }

    //Comment
}
