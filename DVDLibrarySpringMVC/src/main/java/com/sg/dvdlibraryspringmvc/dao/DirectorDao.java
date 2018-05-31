package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Director;

import java.util.List;
import java.util.Map;

public interface DirectorDao {

    public void addDirector(Director director);

    public void removeDirector(int directorId);

    public void updateDirector(Director director);

    public List<Director> getAllDirectors();

    public Director getDirectorById(int directorId);

    public Director getDirectorByLastName(String lastName);

    //public List<Director> searchDirectoris(Map<SearchTerm, String> criteria);
}
