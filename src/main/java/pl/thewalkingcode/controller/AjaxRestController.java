package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.Entry;
import pl.thewalkingcode.model.ShowCriteriaDTO;
import pl.thewalkingcode.service.EntryQueryService;
import pl.thewalkingcode.service.IEntryQueryService;

import java.time.LocalDate;


@RestController
@RequestMapping(value = "/ajax")
public class AjaxRestController {

    @Autowired
    IEntryQueryService entryQueryService;

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String show(@RequestBody ShowCriteriaDTO showCriteriaDTO) {
        LocalDate start = changeDate(showCriteriaDTO.getDataStart());
        LocalDate end = changeDate(showCriteriaDTO.getDataEnd());
        System.out.println(entryQueryService.getEntries(SecurityContextHolder.getContext().getAuthentication().getName(), start, end));
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