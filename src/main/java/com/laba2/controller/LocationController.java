package com.laba2.controller;

import com.laba2.dao.daoImpl.DaoLocationImpl;
import com.laba2.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocationController {

    DaoLocationImpl daoLocation;

    @Autowired
    public void setDaoLocation(DaoLocationImpl daoLocation) {
        this.daoLocation = daoLocation;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/addLocation", method = RequestMethod.GET)
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView("add", "command", new Location());
        modelAndView.addObject("task", 4);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/saveLocation", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Location location) {
        daoLocation.createLocation(location);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteLocation/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int id) {
        daoLocation.deleteLocation(id);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }
}
