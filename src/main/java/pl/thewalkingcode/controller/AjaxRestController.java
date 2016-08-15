package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.ShowCriteriaDTO;
import pl.thewalkingcode.service.IEntriesQueryService;

import java.time.LocalDate;


@RestController
@RequestMapping(value = "/ajax")
public class AjaxRestController {

    private IEntriesQueryService entriesQueryService;

    @Autowired
    public AjaxRestController(IEntriesQueryService entriesQueryService) {
        this.entriesQueryService = entriesQueryService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String show(@RequestBody ShowCriteriaDTO showCriteriaDTO) {
        LocalDate start = changeDate(showCriteriaDTO.getDataStart());
        LocalDate end = changeDate(showCriteriaDTO.getDataEnd());
        entriesQueryService.getAllEntries(SecurityContextHolder.getContext().getAuthentication().getName());
        return "request";
    }

    private LocalDate changeDate(String dateToSplit) {
        String[] dateSplit = dateToSplit.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        return LocalDate.of(year, month, day);
    }

}