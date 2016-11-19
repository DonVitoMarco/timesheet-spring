package pl.thewalkingcode.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.EntryQueryDTO;
import pl.thewalkingcode.model.ShowCriteriaFormDTO;
import pl.thewalkingcode.model.UserQueryDTO;
import pl.thewalkingcode.service.IDepartmentsQueryService;
import pl.thewalkingcode.service.IEntriesQueryService;
import pl.thewalkingcode.service.IUserQueryService;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/query")
public class AjaxQueryRestController {

    private final static Logger logger = Logger.getLogger(AjaxQueryRestController.class);

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
        logger.debug("Show Entries: " + showCriteriaDTO);
        return entriesQueryService.getAllEntries(SecurityContextHolder.getContext().getAuthentication().getName(),
                Date.valueOf(showCriteriaDTO.getDataStart()), Date.valueOf(showCriteriaDTO.getDataEnd()));
    }

    @RequestMapping(value = "/users")
    public List<UserQueryDTO> showAllUsers() {
        logger.debug("Show All Users");
        return userQueryService.getAllUsers();
    }

    @RequestMapping(value = "/entries")
    public List<EntryQueryDTO> showAllEntries(@RequestBody ShowCriteriaFormDTO showCriteriaFormDTO) {
        logger.debug("Show All Entries: " + showCriteriaFormDTO);
        return entriesQueryService.getAllEntriesAllUsers(Date.valueOf(showCriteriaFormDTO.getDataStart()),
                Date.valueOf(showCriteriaFormDTO.getDataEnd()));
    }

    @RequestMapping(value = "/departments")
    public List<String> showAllDepartments() {
        logger.debug("Show All Departments");
        return departmentsQueryService.getAllDepartments();
    }

}