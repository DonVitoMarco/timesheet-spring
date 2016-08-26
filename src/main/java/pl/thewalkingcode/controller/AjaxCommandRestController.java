package pl.thewalkingcode.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.DepartmentFormDTO;
import pl.thewalkingcode.model.EntryCommandDTO;
import pl.thewalkingcode.model.EntryDeleteFormDTO;
import pl.thewalkingcode.model.EntryFormDTO;
import pl.thewalkingcode.service.IDepartmentsCommandService;
import pl.thewalkingcode.service.IEntriesCommandService;
import pl.thewalkingcode.service.IUserCommandService;


@RestController
@RequestMapping(value = "/ajax/command")
public class AjaxCommandRestController {

    private final static Logger logger = Logger.getLogger(AjaxCommandRestController.class);

    private IEntriesCommandService entriesCommandService;
    private IUserCommandService userCommandService;
    private IDepartmentsCommandService departmentsCommandService;

    @Autowired
    public AjaxCommandRestController(IEntriesCommandService entriesCommandService, IUserCommandService userCommandService,
                                     IDepartmentsCommandService departmentsCommandService) {
        this.entriesCommandService = entriesCommandService;
        this.userCommandService = userCommandService;
        this.departmentsCommandService = departmentsCommandService;
    }


    @RequestMapping(value = "/add")
    public ResponseEntity addEntry(@RequestBody EntryFormDTO entryFormDTO) {
        logger.debug("Add Entry: " + entryFormDTO);
        boolean result = entriesCommandService.addNewEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        logger.debug(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/edit")
    public ResponseEntity editEntry(@RequestBody EntryFormDTO entryFormDTO) {
        logger.debug("Edit Entry: " + entryFormDTO);
        EntryCommandDTO entry = entriesCommandService.editEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(entry);
    }

    @RequestMapping(value = "/del")
    public ResponseEntity delEntry(@RequestBody EntryDeleteFormDTO entryDeleteFormDTO) {
        logger.debug("Delete Entry: " + entryDeleteFormDTO);
        int countEntry = entriesCommandService.deleteEntry(entryDeleteFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(countEntry);
    }

    @RequestMapping(value = "/changeRole")
    public ResponseEntity changeRole(@RequestBody String index) {
        logger.debug("Change Role: " + index.split("=")[1]);
        int affectedRows = userCommandService.changeRoleUser(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(affectedRows);
    }

    @RequestMapping(value = "/changeEnable")
    public ResponseEntity changeEnable(@RequestBody String index) {
        logger.debug("Change Enable: " + index.split("=")[1]);
        int affectedRows = userCommandService.changeEnableUser(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(affectedRows);
    }

    @RequestMapping(value = "/approve")
    public ResponseEntity approveEntry(@RequestBody String index) {
        logger.debug("Approve Entry: " + index.split("=")[1]);
        boolean result = entriesCommandService.approveEntry(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/notapprove")
    public ResponseEntity notApproveEntry(@RequestBody String index) {
        logger.debug("Not Approve Entry: " + index.split("=")[1]);
        boolean result = entriesCommandService.notApproveEntry(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/changeDepartment")
    public boolean changeDepartmentStatus(@RequestBody DepartmentFormDTO departmentFormDTO) {
        logger.debug("Change Department Status: " + departmentFormDTO);
        if (departmentFormDTO.getAction().equals("add")) {
            return departmentsCommandService.addDepartments(departmentFormDTO.getName());
        }
        if (departmentFormDTO.getAction().equals("delete")) {
            return departmentsCommandService.deleteDepartments(departmentFormDTO.getName());
        }
        return false;
    }

}
