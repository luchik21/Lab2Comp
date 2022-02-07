package com.laba2.controller;

import com.laba2.dao.daoImpl.DaoJobImpl;
import com.laba2.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JobController {

    private DaoJobImpl daoJob;

    @Autowired
    public void setDaoJob(DaoJobImpl daoJob) {
        this.daoJob = daoJob;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/addJob", method = RequestMethod.GET)
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView("add", "command", new Job());
        modelAndView.addObject("task", 3);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/saveJob", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Job job) {
        daoJob.createJob(job);
        return new ModelAndView("redirect:/expand");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteJob/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int id) {
        daoJob.deleteJob(id);
        return new ModelAndView("redirect:/expand");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/editJob/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployee(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit", "command", new Job());
        modelAndView.addObject("job", daoJob.findById(id));
        modelAndView.addObject("task", 3);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/editJob/saveEditJob", method = RequestMethod.POST)
    public ModelAndView saveEditEmployee(@ModelAttribute Job job) {
        daoJob.editJob(job);
        return new ModelAndView("redirect:/expand");
    }
}
