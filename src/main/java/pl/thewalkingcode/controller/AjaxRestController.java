package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.ShowCriteriaFormDTO;
import pl.thewalkingcode.service.IEntriesQueryService;

import java.sql.Date;


@RestController
@RequestMapping(value = "/ajax")
public class AjaxRestController {

    private IEntriesQueryService entriesQueryService;

    @Autowired
    public AjaxRestController(IEntriesQueryService entriesQueryService) {
        this.entriesQueryService = entriesQueryService;
    }

    @RequestMapping(value = "/show")
    public String show(@RequestBody ShowCriteriaFormDTO showCriteriaDTO) {
        System.out.println(showCriteriaDTO.toString());
        System.out.println(Date.valueOf(showCriteriaDTO.getDataStart()));
        System.out.println(Date.valueOf(showCriteriaDTO.getDataEnd()));
        entriesQueryService.getAllEntries(SecurityContextHolder.getContext().getAuthentication().getName(),
                Date.valueOf(showCriteriaDTO.getDataStart()), Date.valueOf(showCriteriaDTO.getDataEnd()));
        return "request";
    }

//    private LocalDate changeDate(String dateToSplit) {
//        String[] dateSplit = dateToSplit.split("-");
//        int year = Integer.parseInt(dateSplit[0]);
//        int month = Integer.parseInt(dateSplit[1]);
//        int day = Integer.parseInt(dateSplit[2]);
//        return LocalDate.of(year, month, day);
//    }

}