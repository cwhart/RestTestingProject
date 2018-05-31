package com.sg.dvdlibraryspringmvc.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

public class Dvd {

    int dvdId;
    @NotEmpty(message = "You must supply a value for DVD Title.")
    @Length(max = 50, message = "DVD Title must be no more than 50 characters in length.")
    String dvdTitle;
    //@NotEmpty(message = "You must supply a value for Release Year.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@Pattern(regexp="[\\d]{4}", message = "Please enter a valid 4-digit year.")
    LocalDate releaseDate;
    //@NotEmpty(message = "You must supply a value for Director Name.")
    //@Length(max = 50, message = "Director Name must be no more than 50 characters in length.")
    //String director;
    Director director;
    @NotEmpty(message = "You must supply a value for Rating.")
    String rating;
    String notes;

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getDvdTitle() {
        return dvdTitle;
    }

    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    //public String getDirector() {
    //    return director;
    //}

    //public void setDirector(String director) {
    //    this.director = director;
    //}

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return dvdId == dvd.dvdId &&
                Objects.equals(dvdTitle, dvd.dvdTitle) &&
                Objects.equals(releaseDate, dvd.releaseDate) &&
                Objects.equals(director, dvd.director) &&
                Objects.equals(rating, dvd.rating) &&
                Objects.equals(notes, dvd.notes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dvdId, dvdTitle, releaseDate, director, rating, notes);
    }
}
