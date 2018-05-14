package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdListDao;
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
import java.util.List;

@Controller
public class DVDLibraryController {

    DvdListDao dao;

    @Inject
    public DVDLibraryController(DvdListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/displayDvdsPage", method=RequestMethod.GET)
    public String displayDvdsPage(Model model) {
        List<Dvd> dvdList = dao.getAllDvds();

        model.addAttribute("dvdList", dvdList);

        return "dvds";
    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    public String createDvd(HttpServletRequest request) {

        Dvd dvd = new Dvd();
        dvd.setDvdTitle(request.getParameter("title"));
        dvd.setDirector(request.getParameter("directorName"));
        dvd.setReleaseYear(request.getParameter("releaseYear"));
        dvd.setRating(request.getParameter("rating"));
        dvd.setNotes(request.getParameter("notes"));

        dao.addDvd(dvd);

        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayDvdDetails", method = RequestMethod.GET)
    public String displayDvdDetails(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdParameter);

        Dvd dvd = dao.getDvdById(dvdId);

        model.addAttribute("dvd", dvd);

        return "dvdDetails";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        dao.removeDvd(dvdId);

        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayEditDvdForm", method = RequestMethod.GET)
    public String displayEditDvdForm(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        Dvd dvd = dao.getDvdById(dvdId);

        model.addAttribute("dvd", dvd);
        return "editDvdForm";
    }

    @RequestMapping (value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {

        if(result.hasErrors()) {
            return "editDvdForm";
        }
        dao.updateDvd(dvd);

        return "redirect: displayDvdsPage";
    }
}
