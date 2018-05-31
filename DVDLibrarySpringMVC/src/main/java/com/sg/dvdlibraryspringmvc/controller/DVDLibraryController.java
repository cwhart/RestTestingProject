package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DirectorDao;
import com.sg.dvdlibraryspringmvc.dao.DvdListDao;
import com.sg.dvdlibraryspringmvc.model.Director;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DVDLibraryController {

    DvdListDao dvdDao;
    DirectorDao directorDao;

    @Inject
    public DVDLibraryController(DvdListDao dvdDao, DirectorDao directorDao) {
        this.dvdDao = dvdDao;
        this.directorDao = directorDao;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcomePage() {
        return "redirect:displayDvdsPage";
    }


    @RequestMapping(value="/displayDvdsPage", method=RequestMethod.GET)
    public String displayDvdsPage(Model model) {
        List<Dvd> dvdList = dvdDao.getAllDvds();

        model.addAttribute("dvdList", dvdList);

        return "dvds";
    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    public String createDvd(HttpServletRequest request) {

        Dvd dvd = new Dvd();
        dvd.setDvdTitle(request.getParameter("title"));
        String directorName = request.getParameter("directorName");
        dvd.setDirector(directorDao.getDirectorByLastName(directorName));
        dvd.setReleaseDate(LocalDate.parse(request.getParameter("releaseDate")));
        dvd.setRating(request.getParameter("rating"));
        dvd.setNotes(request.getParameter("notes"));

        dvdDao.addDvd(dvd);

        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayDvdDetails", method = RequestMethod.GET)
    public String displayDvdDetails(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdParameter);

        Dvd dvd = dvdDao.getDvdById(dvdId);

        model.addAttribute("dvd", dvd);

        return "dvdDetails";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {
        String dvdIdParameter = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdParameter);
        dvdDao.removeDvd(dvdId);

        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayEditDvdForm", method = RequestMethod.GET)
    public String displayEditDvdForm(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdParameter);
        Dvd dvd = dvdDao.getDvdById(dvdId);

        model.addAttribute("dvd", dvd);
        return "editDvdForm";
    }

    @RequestMapping (value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {

        if(result.hasErrors()) {
            return "editDvdForm";
        }

        String directorLastName = dvd.getDirector().getLast_name();
        dvd.setDirector(directorDao.getDirectorByLastName(directorLastName));
        dvdDao.updateDvd(dvd);

        return "redirect: displayDvdsPage";
    }
}
