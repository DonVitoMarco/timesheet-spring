package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.EntryDeleteFormDTO;
import pl.thewalkingcode.model.EntryFormDTO;
import pl.thewalkingcode.service.IEntriesCommandService;


@RestController
@RequestMapping(value = "/ajax/command")
public class AjaxCommandRestController {

    private IEntriesCommandService commandService;

    @Autowired
    public AjaxCommandRestController(IEntriesCommandService commandService) {
        this.commandService = commandService;
    }


    @RequestMapping(value = "/add")
    public void addEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println("ENTRY FORM DTO: " + entryFormDTO.toString());
        commandService.addNewEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = "/edit")
    public void editEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println("ENTRY FORM DTO: " + entryFormDTO.toString());
        commandService.editEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = "/del")
    public void delEntry(@RequestBody EntryDeleteFormDTO entryDeleteFormDTO) {
        System.out.println(entryDeleteFormDTO.toString());
        commandService.deleteEntry(entryDeleteFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
