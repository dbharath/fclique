package net.pcheck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(){	
 
        return "/home";
    }
}