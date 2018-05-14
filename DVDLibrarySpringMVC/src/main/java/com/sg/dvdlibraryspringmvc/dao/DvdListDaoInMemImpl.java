package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DvdListDaoInMemImpl implements DvdListDao{

    private Map<Long, Dvd> dvdMap = new HashMap<>();
    private static long dvdIdCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(long dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.replace(dvd.getDvdId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> dvdList = dvdMap.values();
        return new ArrayList(dvdList);
    }

    @Override
    public Dvd getDvdById(long dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        String dvdTitleSearchCriteria = criteria.get(SearchTerm.DVD_TITLE);
        String dvdDirectorSearchCriteria = criteria.get(SearchTerm.DVD_DIRECTOR);
        String dvdReleaseYearSearchCriteria = criteria.get(SearchTerm.DVD_RELEASE_YEAR);
        String dvdRatingSearchCriteria = criteria.get(SearchTerm.DVD_RATING);
        String dvdNotesSearchCriteria = criteria.get(SearchTerm.DVD_NOTES);

        Predicate<Dvd> titleMatchPredicate;
        Predicate<Dvd> directorMatchPredicate;
        Predicate<Dvd> releaseYearMatchPredicate;
        Predicate<Dvd> ratingMatchPredicate;
        Predicate<Dvd> notesMatchPredicate;

        Predicate<Dvd> truePredicate = (c) -> {
            return true;
        };

        if(dvdTitleSearchCriteria == null || dvdTitleSearchCriteria.isEmpty()) {
            titleMatchPredicate = truePredicate;
        } else {
            titleMatchPredicate = (c) -> c.getDvdTitle().equals(dvdTitleSearchCriteria);
        }

        if(dvdDirectorSearchCriteria == null || dvdDirectorSearchCriteria.isEmpty()) {
            directorMatchPredicate = truePredicate;
        } else {
            directorMatchPredicate = (c) -> c.getDirector().equals(dvdDirectorSearchCriteria);
        }

        if(dvdReleaseYearSearchCriteria == null || dvdReleaseYearSearchCriteria.isEmpty()) {
            releaseYearMatchPredicate = truePredicate;
        } else {
            releaseYearMatchPredicate = (c) -> c.getReleaseYear().equals(dvdReleaseYearSearchCriteria);
        }

        if(dvdRatingSearchCriteria == null || dvdRatingSearchCriteria.isEmpty()) {
            ratingMatchPredicate = truePredicate;
        } else {
            ratingMatchPredicate = (c) -> c.getRating().equals(dvdRatingSearchCriteria);
        }

        if(dvdNotesSearchCriteria == null || dvdNotesSearchCriteria.isEmpty()) {
            notesMatchPredicate = truePredicate;
        } else {
            notesMatchPredicate = (c) -> c.getNotes().equals(dvdNotesSearchCriteria);
        }

        return dvdMap.values().stream()
                .filter(titleMatchPredicate
                .and(directorMatchPredicate)
                .and(releaseYearMatchPredicate)
                .and(ratingMatchPredicate)
                .and(notesMatchPredicate))
                .collect(Collectors.toList());

    }
}
