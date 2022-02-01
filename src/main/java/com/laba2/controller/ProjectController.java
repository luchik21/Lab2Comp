package com.laba2.controller;

import com.laba2.dao.daoImpl.DaoCustomerImpl;
import com.laba2.dao.daoImpl.DaoLocationImpl;
import com.laba2.dao.daoImpl.DaoProjectImpl;
import com.laba2.dao.daoImpl.DaoRequirementsImpl;
import com.laba2.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {

    DaoProjectImpl daoProject;
    DaoCustomerImpl daoCustomer;
    DaoRequirementsImpl daoRequirements;
    DaoLocationImpl daoLocation;

    @Autowired
    public void setDaoProject(DaoProjectImpl daoProject) {
        this.daoProject = daoProject;
    }

    @Autowired
    public void setDaoCustomer(DaoCustomerImpl daoCustomer) {
        this.daoCustomer = daoCustomer;
    }

    @Autowired
    public void setDaoRequirements(DaoRequirementsImpl daoRequirements) {
        this.daoRequirements = daoRequirements;
    }

    @Autowired
    public void setDaoLocation(DaoLocationImpl daoLocation) {
        this.daoLocation = daoLocation;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/addProject", method = RequestMethod.GET)
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView("add", "command", new Project());
        modelAndView.addObject("locations", daoLocation.selectAllLocation());
        modelAndView.addObject("customers", daoCustomer.selectAllCustomers());
        modelAndView.addObject("requirements", daoRequirements.selectAllRequirements());
        modelAndView.addObject("task", 2);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Project project) {
        daoProject.createProject(project);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/deleteProject/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int id) {
        daoProject.deleteProject(id);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/editProject/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployee(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit", "command", new Project());
        modelAndView.addObject("project", daoProject.findById(id));
        modelAndView.addObject("locations", daoLocation.selectAllLocation());
        modelAndView.addObject("customers", daoCustomer.selectAllCustomers());
        modelAndView.addObject("requirements", daoRequirements.selectAllRequirements());
        modelAndView.addObject("task", 2);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/editProject/saveEditProject", method = RequestMethod.POST)
    public ModelAndView saveEditEmployee(@ModelAttribute Project project) {
        daoProject.editProject(project);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/seeAllAboutProjects", method = RequestMethod.GET)
    public ModelAndView seeAllAboutProjects() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projects", daoProject.selectAllProject());
        modelAndView.addObject("locations", daoLocation.selectAllLocation());
        modelAndView.addObject("customers", daoCustomer.selectAllCustomers());
        modelAndView.addObject("requirements", daoRequirements.selectAllRequirements());
        modelAndView.addObject("do", 1);
        modelAndView.setViewName("viewAllProjects");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/findByCustomer/{id}", method = RequestMethod.GET)
    public ModelAndView findProjectByCustomer(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        if(id == 0){
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("projects", daoProject.findByCustomerId(id));
        modelAndView.addObject("findId", id);
        modelAndView.addObject("task", 4);
        modelAndView.setViewName("find");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/findByLocation/{id}", method = RequestMethod.GET)
    public ModelAndView findProjectByLocation(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        if(id == 0){
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("projects", daoProject.findByLocationId(id));
        modelAndView.addObject("findId", id);
        modelAndView.addObject("task", 3);
        modelAndView.setViewName("find");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/findByRequirements/{id}", method = RequestMethod.GET)
    public ModelAndView findProjectByRequirements(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        if(id == 0){
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("projects", daoProject.findByRequirementsId(id));
        modelAndView.addObject("findId", id);
        modelAndView.addObject("task", 5);
        modelAndView.setViewName("find");
        return modelAndView;
    }
}
