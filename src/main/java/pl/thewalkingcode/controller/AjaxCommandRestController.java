package pl.thewalkingcode.controller;

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
        System.out.println("ENTRY FORM DTO: " + entryFormDTO.toString());
        EntryCommandDTO entry = entriesCommandService.addNewEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(entry);
    }

    @RequestMapping(value = "/edit")
    public ResponseEntity editEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println("ENTRY FORM DTO: " + entryFormDTO.toString());
        EntryCommandDTO entry = entriesCommandService.editEntry(entryFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(entry);
    }

    @RequestMapping(value = "/del")
    public ResponseEntity delEntry(@RequestBody EntryDeleteFormDTO entryDeleteFormDTO) {
        System.out.println(entryDeleteFormDTO.toString());
        int countEntry = entriesCommandService.deleteEntry(entryDeleteFormDTO,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(countEntry);
    }

    @RequestMapping(value = "/changeRole")
    public ResponseEntity changeRole(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        int affectedRows = userCommandService.changeRoleUser(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(affectedRows);
    }

    @RequestMapping(value = "/changeEnable")
    public ResponseEntity changeEnable(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        int affectedRows = userCommandService.changeEnableUser(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(affectedRows);
    }

    @RequestMapping(value = "/approve")
    public ResponseEntity approveEntry(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        boolean suc = entriesCommandService.approveEntry(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(suc);
    }

    @RequestMapping(value = "/notapprove")
    public ResponseEntity notApproveEntry(@RequestBody String index) {
        System.out.println(index.split("=")[1]);
        boolean suc = entriesCommandService.notApproveEntry(index.split("=")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(suc);
    }

    @RequestMapping(value = "/changeDepartment")
    public boolean changeDepartmentStatus(@RequestBody DepartmentFormDTO departmentFormDTO) {
        System.out.println(departmentFormDTO.toString());
        if(departmentFormDTO.getAction().equals("add")) {
            return departmentsCommandService.addDepartments(departmentFormDTO.getName());
        }
        if(departmentFormDTO.getAction().equals("delete")) {
            return departmentsCommandService.deleteDepartments(departmentFormDTO.getName());
        }
        return false;
    }

}
