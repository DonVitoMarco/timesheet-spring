package pl.thewalkingcode.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/timesheet")
public class TimesheetController {

    private final static Logger logger = Logger.getLogger(TimesheetController.class);


    @RequestMapping(method = RequestMethod.GET)
    public String timesheet(Model model) {
        logger.debug("Timesheet Page");
        return "timesheet";
    }

}
