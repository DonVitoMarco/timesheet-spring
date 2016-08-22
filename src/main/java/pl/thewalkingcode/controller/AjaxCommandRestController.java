package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.EntryCommandDTO;
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
    public ResponseEntity addEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println("ENTRY FORM DTO: " + entryFormDTO.toString());
        EntryCommandDTO entry = commandService.addNewEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(entry);
    }

    @RequestMapping(value = "/edit")
    public ResponseEntity editEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println("ENTRY FORM DTO: " + entryFormDTO.toString());
        EntryCommandDTO entry = commandService.editEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(entry);
    }

    @RequestMapping(value = "/del")
    public ResponseEntity delEntry(@RequestBody EntryDeleteFormDTO entryDeleteFormDTO) {
        System.out.println(entryDeleteFormDTO.toString());
        int countEntry = commandService.deleteEntry(entryDeleteFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(countEntry);
    }

}
