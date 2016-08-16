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
@RequestMapping(value = "/ajax")
public class AjaxRestController {

    private IEntriesQueryService entriesQueryService;
    private IEntriesCommandService entriesCommandService;

    @Autowired
    public AjaxRestController(IEntriesQueryService entriesQueryService, IEntriesCommandService entriesCommandService) {
        this.entriesQueryService = entriesQueryService;
        this.entriesCommandService = entriesCommandService;
    }

    @RequestMapping(value = "/show")
    public List<EntryQueryDTO> showEntries(@RequestBody ShowCriteriaFormDTO showCriteriaDTO) {
        //TODO logger
        System.out.println(showCriteriaDTO.toString());
        return entriesQueryService.getAllEntries(SecurityContextHolder.getContext().getAuthentication().getName(),
                Date.valueOf(showCriteriaDTO.getDataStart()), Date.valueOf(showCriteriaDTO.getDataEnd()));
    }

    @RequestMapping(value = "/add")
    public String addEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println(entryFormDTO.toString());
        return "response";
    }

//    private LocalDate changeDate(String dateToSplit) {
//        String[] dateSplit = dateToSplit.split("-");
//        int year = Integer.parseInt(dateSplit[0]);
//        int month = Integer.parseInt(dateSplit[1]);
//        int day = Integer.parseInt(dateSplit[2]);
//        return LocalDate.of(year, month, day);
//    }

}