package com.sg.dvdlibrary.dto;

import java.time.LocalDate;

public class Dvd {

    //DVD class - just properties for DVD attributes, and getters and setters.
    //No setter for DVD name since this is the key.

    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userComment;

    public Dvd (String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dvd dvd = (Dvd) o;

        if (!title.equals(dvd.title)) return false;
        if (!releaseDate.equals(dvd.releaseDate)) return false;
        if (!mpaaRating.equals(dvd.mpaaRating)) return false;
        if (!directorName.equals(dvd.directorName)) return false;
        if (!studio.equals(dvd.studio)) return false;
        return userComment.equals(dvd.userComment);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + mpaaRating.hashCode();
        result = 31 * result + directorName.hashCode();
        result = 31 * result + studio.hashCode();
        result = 31 * result + userComment.hashCode();
        return result;
    }
    //Comment
}
