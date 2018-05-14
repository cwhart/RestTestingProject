package com.sg.dvdlibraryspringmvc.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Dvd {

    long dvdId;
    @NotEmpty(message = "You must supply a value for DVD Title.")
    @Length(max = 50, message = "DVD Title must be no more than 50 characters in length.")
    String dvdTitle;
    @NotEmpty(message = "You must supply a value for Release Year.")
    @Pattern(regexp="[\\d]{4}", message = "Please enter a valid 4-digit year.")
    String releaseYear;
    @NotEmpty(message = "You must supply a value for Director Name.")
    @Length(max = 50, message = "Director Name must be no more than 50 characters in length.")
    String director;
    @NotEmpty(message = "You must supply a value for Rating.")
    String rating;
    String notes;

    public long getDvdId() {
        return dvdId;
    }

    public void setDvdId(long dvdId) {
        this.dvdId = dvdId;
    }

    public String getDvdTitle() {
        return dvdTitle;
    }

    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

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
        return releaseYear == dvd.releaseYear &&
                Objects.equals(dvdTitle, dvd.dvdTitle) &&
                Objects.equals(director, dvd.director) &&
                Objects.equals(rating, dvd.rating) &&
                Objects.equals(notes, dvd.notes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dvdTitle, releaseYear, director, rating, notes);
    }
}
