package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.EntryFormDTO;
import pl.thewalkingcode.model.EntryQueryDTO;
import pl.thewalkingcode.model.ShowCriteriaFormDTO;
import pl.thewalkingcode.service.IEntriesCommandService;
import pl.thewalkingcode.service.IEntriesQueryService;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/query")
public class AjaxQueryRestController {

    private IEntriesQueryService entriesQueryService;

    @Autowired
    public AjaxQueryRestController(IEntriesQueryService entriesQueryService) {
        this.entriesQueryService = entriesQueryService;
    }

    @RequestMapping(value = "/show")
    public List<EntryQueryDTO> showEntries(@RequestBody ShowCriteriaFormDTO showCriteriaDTO) {
        //TODO logger
        System.out.println(showCriteriaDTO.toString());
        return entriesQueryService.getAllEntries(SecurityContextHolder.getContext().getAuthentication().getName(),
                Date.valueOf(showCriteriaDTO.getDataStart()), Date.valueOf(showCriteriaDTO.getDataEnd()));
    }



}