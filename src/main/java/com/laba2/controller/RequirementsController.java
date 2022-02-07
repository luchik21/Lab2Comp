package com.laba2.controller;

import com.laba2.dao.daoImpl.DaoRequirementsImpl;
import com.laba2.models.Requirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequirementsController {

    private DaoRequirementsImpl daoRequirements;

    @Autowired
    public void setDaoRequirements(DaoRequirementsImpl daoRequirements) {
        this.daoRequirements = daoRequirements;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/addRequirements", method = RequestMethod.GET)
    public ModelAndView addRequirements() {
        ModelAndView modelAndView = new ModelAndView("add", "command", new Requirements());
        modelAndView.addObject("task", 6);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/saveRequirements", method = RequestMethod.POST)
    public ModelAndView saveRequirements(@ModelAttribute Requirements requirements) {
        daoRequirements.createRequirements(requirements);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteRequirements/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRequirements(@PathVariable int id) {
        daoRequirements.deleteRequirements(id);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }
}
