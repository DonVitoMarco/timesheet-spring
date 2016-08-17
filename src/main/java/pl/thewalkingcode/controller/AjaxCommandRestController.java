package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.EntryFormDTO;
import pl.thewalkingcode.service.IEntriesCommandService;

import java.sql.Time;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;


@RestController
@RequestMapping(value = "/ajax/command")
public class AjaxCommandRestController {

    private IEntriesCommandService commandService;

    @Autowired
    public AjaxCommandRestController(IEntriesCommandService commandService) {
        this.commandService = commandService;
    }

    @RequestMapping(value = "/add")
    public String addEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println(entryFormDTO.toString());
        commandService.addNewEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return "response";
    }

}
