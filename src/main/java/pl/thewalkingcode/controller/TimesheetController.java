package pl.thewalkingcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TimesheetController {

    @RequestMapping(value = "/timesheet", method = RequestMethod.GET)
    public String timesheet(Model model) {
        return "timesheet";
    }

}
