package pl.thewalkingcode.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    private final static Logger logger = Logger.getLogger(HomeController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        logger.debug("Home Page");
        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        logger.debug("About Page");
        return "about";
    }

}
