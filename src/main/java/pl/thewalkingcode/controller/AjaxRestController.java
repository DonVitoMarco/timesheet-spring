package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.Entry;
import pl.thewalkingcode.model.ShowCriteriaDTO;
import pl.thewalkingcode.service.EntryQueryService;


@RestController
@RequestMapping(value = "/ajax")
public class AjaxRestController {

    @Autowired
    EntryQueryService entryQueryService;

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String show(@RequestBody ShowCriteriaDTO showCriteriaDTO) {
        System.out.println(showCriteriaDTO.toString());
        System.out.println(entryQueryService.getEntries("marek", "8", "9").toString());
        return "request";
    }

}