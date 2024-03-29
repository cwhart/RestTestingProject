package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.io.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DvdDaoFileImpl implements DvdDao {

    //Filename and field delimiter
    public static final String DVD_FILE = "dvdLibrary.txt";
    public static final String DELIMITER = "::";

    //Hashmap to store DVD library
    Map<String, Dvd> dvds = new HashMap<>();

    //Load DVDs from file first, then add the newly created one, and write back to the file.
    public Dvd addDvd(String dvdName, Dvd dvd) throws DvdLibraryPersistenceException {
        loadDvds();
        Dvd newDvd = dvds.put(dvdName, dvd);
        writeDvds();
        return newDvd;
    }

    //Load DVDs from the file first, then display all.
    public List<Dvd> displayAll() throws DvdLibraryPersistenceException {
        loadDvds();
        List<Dvd> listOfDvds = new ArrayList<>();
        for (Dvd currentDvd : dvds.values()) {
            listOfDvds.add(currentDvd);
        }

        return listOfDvds;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException {

        //Load DVDs from file, remove one, then write back to the file.
        loadDvds();

            Dvd dvdToRemove = dvds.remove(title);
            writeDvds();
            return dvdToRemove;
        }



    @Override
    public Dvd editDvd(Dvd dvdToEdit) throws DvdLibraryPersistenceException {

        //Load DVDs from file, edit, then write back to the file.
        loadDvds();
        Dvd updatedDvd = dvds.put(dvdToEdit.getTitle(), dvdToEdit);
        writeDvds();
        return dvds.get(dvdToEdit.getTitle());
    }

    @Override
    public Dvd displayDvd(String title) throws DvdLibraryPersistenceException {

        //Load DVDs from file and display.
        loadDvds();
        Dvd dvdToDisplay = dvds.get(title);
        return dvdToDisplay;
    }

    @Override
    public List<Dvd> findMoviesWithSpecifiedRating(String rating) throws DvdLibraryPersistenceException{
        loadDvds();
        return dvds.values()
                .stream()
                .filter(p -> p.getMpaaRating().equals(rating))
                .collect(Collectors.toList());

    }

    @Override
    public List<Dvd> findMoviesReleasedInPastNYears(int numberOfYears) throws DvdLibraryPersistenceException {
        loadDvds();
        LocalDate dateToCompareTo = LocalDate.now().minusYears(numberOfYears);
        return dvds.values()
                .stream()
                .filter(p -> (p.getReleaseDate().isAfter(dateToCompareTo)))
                .collect(Collectors.toList());

    }

    @Override
    public List<Dvd> findMoviesWithSpecifiedDirector(String director) throws DvdLibraryPersistenceException {
        loadDvds();
        return dvds.values()
                .stream()
                .filter(p -> p.getDirectorName().equals(director))
                .sorted(Comparator.comparing(Dvd::getMpaaRating))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> findMoviesBySpecifiedStudio(String studio) throws DvdLibraryPersistenceException{
        loadDvds();
        return dvds.values()
                .stream()
                .filter(p -> p.getStudio().equals(studio))
                .collect(Collectors.toList());
    }

    @Override
    public double findAverageAge() throws DvdLibraryPersistenceException {
        loadDvds();
        return dvds.values()
                .stream()
                .mapToInt(p -> (LocalDate.now().getYear()) - p.getReleaseDate().getYear())
                .average().getAsDouble();


    }

    @Override
    public Dvd findNewestMovie() throws DvdLibraryPersistenceException {
        loadDvds();
        return dvds.values()
                .stream()
                .max(Comparator.comparing(Dvd::getReleaseDate))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Dvd findOldestMovie()throws DvdLibraryPersistenceException {
        loadDvds();
        return dvds.values()
                .stream()
                .min(Comparator.comparing(Dvd::getReleaseDate))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public int findAverageNumberOfNotes()throws DvdLibraryPersistenceException {
        return 0;
    }

    //Method to read the DVD list from file.
    private void loadDvds() throws DvdLibraryPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryPersistenceException("-_- Could not load DVD data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Dvd currentDvd = new Dvd(currentTokens[0]);

            currentDvd.setReleaseDate(LocalDate.parse(currentTokens[1]));
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserComment(currentTokens[5]);

            dvds.put(currentDvd.getTitle(), currentDvd);

        }

        scanner.close();
    }

    //Method to delimit the DVDs in the HashMap and store to file.
    private void writeDvds() throws DvdLibraryPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));

            } catch (IOException e) {
            throw new DvdLibraryPersistenceException("Could not save DVD data." , e);
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

 }
