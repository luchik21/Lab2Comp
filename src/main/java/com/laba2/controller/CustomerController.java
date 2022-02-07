package com.laba2.controller;

import com.laba2.dao.daoImpl.DaoCustomerImpl;
import com.laba2.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

    private DaoCustomerImpl daoCustomer;

    @Autowired
    public void setDaoCustomer(DaoCustomerImpl daoCustomer) {
        this.daoCustomer = daoCustomer;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public ModelAndView addCustomer() {
        ModelAndView modelAndView = new ModelAndView("add", "command", new Customer());
        modelAndView.addObject("task", 5);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS')")
    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Customer customer) {
        daoCustomer.createCustomer(customer);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOSS', 'ROLE_WORKER')")
    @RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int id) {
        daoCustomer.deleteCustomer(id);
        return new ModelAndView("redirect:/seeAllAboutProjects");
    }
}
