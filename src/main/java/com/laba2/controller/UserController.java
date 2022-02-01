package com.laba2.controller;

import com.laba2.dao.daoImpl.DaoUserImpl;
import com.laba2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private DaoUserImpl daoUser;

    @Autowired
    public void setDaoUser(DaoUserImpl daoUser) {
        this.daoUser = daoUser;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView("add", "command", new User());
        modelAndView.addObject("task", 7);
        modelAndView.addObject("roles", daoUser.selectAllUser());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute User user) {
        daoUser.createUser(user);
        return new ModelAndView("redirect:/seeAccounts");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int id) {
        daoUser.deleteUser(id);
        return new ModelAndView("redirect:/seeAccounts");
    }

    @RequestMapping(value = "/seeAccounts", method = RequestMethod.GET)
    public ModelAndView viewAllEmployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listUsers", daoUser.selectAllUser());
        modelAndView.addObject("task", 1);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
