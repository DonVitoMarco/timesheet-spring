package pl.thewalkingcode.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ManageController {

    private final static Logger logger = Logger.getLogger(ManageController.class);


    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage() {
        logger.debug("Manage Page");
        return "manage";
    }

}
