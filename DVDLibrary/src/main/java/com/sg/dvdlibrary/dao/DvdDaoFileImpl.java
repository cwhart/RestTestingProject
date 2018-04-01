package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdDaoFileImpl implements DvdDao {

    //Filename and field delimiter
    public static final String DVD_FILE = "dvdLibrary.txt";
    public static final String DELIMITER = "::";

    //Hashmap to store DVD library
    Map<String, Dvd> dvds = new HashMap<>();

    //Load DVDs from file first, then add the newly created one, and write back to the file.
    public Dvd addDvd(String dvdName, Dvd dvd) throws DvdDaoException {
        loadDvds();
        Dvd newDvd = dvds.put(dvdName, dvd);
        writeDvds();
        return newDvd;
    }

    //Load DVDs from the file first, then display all.
    public List<Dvd> displayAll() throws DvdDaoException {
        loadDvds();
        List<Dvd> listOfDvds = new ArrayList<>();
        for (Dvd currentDvd : dvds.values()) {
            listOfDvds.add(currentDvd);
        }

        return listOfDvds;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdDaoException {

        //Load DVDs from file, remove one, then write back to the file.
        loadDvds();

            Dvd dvdToRemove = dvds.remove(title);
            writeDvds();
            return dvdToRemove;
        }



    @Override
    public Dvd editDvd(Dvd dvdToEdit) throws DvdDaoException {

        //Load DVDs from file, edit, then write back to the file.
        loadDvds();
        Dvd updatedDvd = dvds.put(dvdToEdit.getTitle(), dvdToEdit);
        writeDvds();
        return updatedDvd;
    }

    @Override
    public Dvd displayDvd(String title) throws DvdDaoException {

        //Load DVDs from file and display.
        loadDvds();
        Dvd dvdToDisplay = dvds.get(title);
        return dvdToDisplay;
    }


    //Method to read the DVD list from file.
    private void loadDvds() throws DvdDaoException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdDaoException("-_- Could not load DVD data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Dvd currentDvd = new Dvd(currentTokens[0]);

            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserComment(currentTokens[5]);

            dvds.put(currentDvd.getTitle(), currentDvd);

        }

        scanner.close();
    }

    //Method to delimit the DVDs in the HashMap and store to file.
    private void writeDvds() throws DvdDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));

            } catch (IOException e) {
            throw new DvdDaoException("Could not save DVD data." , e);
        }

        List<Dvd> dvdList = this.displayAll();
        for (Dvd currentDvd : dvdList) {
            out.println(currentDvd.getTitle() + DELIMITER
            + currentDvd.getReleaseDate() + DELIMITER
            + currentDvd.getMpaaRating() + DELIMITER
            + currentDvd.getDirectorName() + DELIMITER
            + currentDvd.getStudio() + DELIMITER
            + currentDvd.getUserComment());

            out.flush();
        }

        out.close();

    }

    //Comment
}
