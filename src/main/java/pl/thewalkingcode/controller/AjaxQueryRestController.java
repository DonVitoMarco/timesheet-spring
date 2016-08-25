package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.*;
import pl.thewalkingcode.service.IDepartmentsQueryService;
import pl.thewalkingcode.service.IEntriesCommandService;
import pl.thewalkingcode.service.IEntriesQueryService;
import pl.thewalkingcode.service.IUserQueryService;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/query")
public class AjaxQueryRestController {

    private IEntriesQueryService entriesQueryService;
    private IUserQueryService userQueryService;
    private IDepartmentsQueryService departmentsQueryService;

    @Autowired
    public AjaxQueryRestController(IEntriesQueryService entriesQueryService, IUserQueryService userQueryService, IDepartmentsQueryService departmentsQueryService) {
        this.entriesQueryService = entriesQueryService;
        this.userQueryService = userQueryService;
        this.departmentsQueryService = departmentsQueryService;
    }

    @RequestMapping(value = "/show")
    public List<EntryQueryDTO> showEntries(@RequestBody ShowCriteriaFormDTO showCriteriaDTO) {
        //TODO logger
        System.out.println(showCriteriaDTO.toString());
        return entriesQueryService.getAllEntries(SecurityContextHolder.getContext().getAuthentication().getName(),
                Date.valueOf(showCriteriaDTO.getDataStart()), Date.valueOf(showCriteriaDTO.getDataEnd()));
    }

    @RequestMapping(value = "/users")
    public List<UserQueryDTO> showUsers() {
        return userQueryService.getAllUsers();
    }

    @RequestMapping(value = "/entries")
    public List<EntryQueryDTO> showAllEntries(@RequestBody ShowCriteriaFormDTO showCriteriaFormDTO) {
        System.out.println(showCriteriaFormDTO.toString());
        return entriesQueryService.getAllEntriesAllUsers(Date.valueOf(showCriteriaFormDTO.getDataStart()),
                Date.valueOf(showCriteriaFormDTO.getDataEnd()));
    }

    @RequestMapping(value = "/departments")
    public List<String> showAllDepartments() {
        return departmentsQueryService.getAllDepartments();
    }


}