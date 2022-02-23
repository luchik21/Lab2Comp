package com.laba2.controller;

import com.laba2.dao.daoImpl.ConnectionAPIImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class APIController {

    private ConnectionAPIImpl connectionAPI;

    @Autowired
    public void setConnectionAPI(ConnectionAPIImpl connectionAPI) {
        this.connectionAPI = connectionAPI;
    }

    @RequestMapping(value = "/viewNews", method = RequestMethod.GET)
    public ModelAndView searchNews(@RequestParam(defaultValue = "ua") String country, @RequestParam(defaultValue = "all") String category) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("articles", connectionAPI.getNewsAPI(country, category));
        System.out.println(connectionAPI.getNewsAPI(country, category));
        String[] countries = {"ua", "us", "pl", "ru", "fr"};
        modelAndView.addObject("countries", countries);
        String[] categories = {"all", "business", "entertainment", "health", "science", "sports", "technology"};
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("news", 1);
        modelAndView.setViewName("news");
        return modelAndView;
    }
}
