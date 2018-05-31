package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdListDao;
import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    private DvdListDao dao;

    @Inject
    public SearchController(DvdListDao dao) {
        this.dao = dao;
    }

    /*@RequestMapping(value="/displaySearchPage", method=RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }*/

    /*@RequestMapping(value = "/DVDLibrarySpringMVC/search/dvds", method = RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap) {
        //Create the map of search criteria to send to the DAO
        Map<SearchTerm, String> criteriaMap = new HashMap<>();

        //Determine which search terms have values, translate the String keys into SearchTerm enums,
        //and set the corresponding values appropriately.

        String currentTerm = searchMap.get("dvdTitle");
        if(currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DVD_TITLE, currentTerm);
        }
        currentTerm = searchMap.get("director");
        if(currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DVD_DIRECTOR, currentTerm);
        }

        currentTerm = searchMap.get("releaseYear");
        if(currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DVD_RELEASE_YEAR, currentTerm);
        }
        currentTerm = searchMap.get("rating");
        if(currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DVD_RATING, currentTerm);
        }

        return dao.searchDvds(criteriaMap);
    }*/
}
