package pl.thewalkingcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/timesheet")
public class TimesheetController {

    @RequestMapping(method = RequestMethod.GET)
    public String timesheet(Model model) {
        return "timesheet";
    }

}
