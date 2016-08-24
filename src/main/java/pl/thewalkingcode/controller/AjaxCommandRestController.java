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
import pl.thewalkingcode.service.IUserCommandService;


@RestController
@RequestMapping(value = "/ajax/command")
public class AjaxCommandRestController {

    private IEntriesCommandService commandService;
    private IUserCommandService userCommandService;

    @Autowired
    public AjaxCommandRestController(IEntriesCommandService commandService, IUserCommandService userCommandService) {
        this.commandService = commandService;
        this.userCommandService = userCommandService;
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

    @RequestMapping(value = "/changeRole")
    public ResponseEntity changeRole(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        boolean suc = userCommandService.changeRoleUser(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(suc);
    }

    @RequestMapping(value = "/changeEnable")
    public ResponseEntity changeEnable(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        boolean suc = userCommandService.changeEnableUser(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(suc);
    }

    @RequestMapping(value = "/approve")
    public ResponseEntity approveEntry(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        boolean suc = commandService.approveEntry(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(suc);
    }

    @RequestMapping(value = "/notapprove")
    public ResponseEntity notapproveEntry(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        boolean suc = commandService.notapproveEntry(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(suc);
    }

}
