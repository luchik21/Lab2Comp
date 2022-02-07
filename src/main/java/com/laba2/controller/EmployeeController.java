package com.laba2.controller;

import com.laba2.dao.daoImpl.DaoEmployeeImpl;
import com.laba2.dao.daoImpl.DaoJobImpl;
import com.laba2.dao.daoImpl.DaoProjectImpl;
import com.laba2.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    private DaoEmployeeImpl daoEmployee;
    private DaoJobImpl daoJob;
    private DaoProjectImpl daoProject;

    @Autowired
    public void setDaoEmployee(DaoEmployeeImpl daoEmployee) {
        this.daoEmployee = daoEmployee;
    }

    @Autowired
    public void setDaoJob(DaoJobImpl daoJob) {
        this.daoJob = daoJob;
    }

    @Autowired
    public void setDaoProject(DaoProjectImpl daoProject) {
        this.daoProject = daoProject;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView viewAllEmployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listEmployee", daoEmployee.selectAllEmployee());
        modelAndView.addObject("task", 1);
        modelAndView.setViewName("viewAll");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView("add", "command", new Employee());
        modelAndView.addObject("task", 1);
        modelAndView.addObject("projects", daoProject.selectAllProject());
        modelAndView.addObject("jobs", daoJob.selectAllJob());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        daoEmployee.createEmployee(employee);
        return new ModelAndView("redirect:/viewAll");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int id) {
        daoEmployee.deleteEmployee(id);
        return new ModelAndView("redirect:/viewAll");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/editEmployee/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployee(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit", "command", new Employee());
        modelAndView.addObject("employee", daoEmployee.findById(id));
        modelAndView.addObject("projects", daoProject.selectAllProject());
        modelAndView.addObject("jobs", daoJob.selectAllJob());
        modelAndView.addObject("task", 1);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/editEmployee/saveEditEmployee", method = RequestMethod.POST)
    public ModelAndView saveEditEmployee(@ModelAttribute Employee employee) {
        daoEmployee.editEmployee(employee);
        return new ModelAndView("redirect:/viewAll");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/expand", method = RequestMethod.GET)
    public ModelAndView expand() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projects", daoProject.selectAllProject());
        modelAndView.addObject("listEmployee", daoEmployee.selectAllEmployee());
        modelAndView.addObject("jobs", daoJob.selectAllJob());
        modelAndView.addObject("more", 1);
        modelAndView.addObject("task", 1);
        modelAndView.setViewName("viewAll");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/findByProject/{id}", method = RequestMethod.GET)
    public ModelAndView findEmployeeByProject(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id == 0) {
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("listEmployee", daoEmployee.findByProjectId(id));
        modelAndView.addObject("findId", id);
        modelAndView.addObject("task", 1);
        modelAndView.setViewName("find");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/findByJob/{id}", method = RequestMethod.GET)
    public ModelAndView findEmployeeByJob(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id == 0) {
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("listEmployee", daoEmployee.findByJobId(id));
        modelAndView.addObject("findId", id);
        modelAndView.addObject("task", 2);
        modelAndView.setViewName("find");
        return modelAndView;
    }
}
