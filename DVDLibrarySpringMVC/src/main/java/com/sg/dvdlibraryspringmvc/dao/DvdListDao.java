package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;

import java.util.List;
import java.util.Map;

public interface DvdListDao {

    public Dvd addDvd(Dvd dvd);

    public void removeDvd(long dvdId);

    public void updateDvd(Dvd dvd);

    public List<Dvd> getAllDvds();

    public Dvd getDvdById(long dvdId);

    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);

}
