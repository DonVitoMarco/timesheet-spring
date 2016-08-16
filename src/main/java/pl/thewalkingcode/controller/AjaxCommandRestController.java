package pl.thewalkingcode.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.EntryFormDTO;


@RestController
@RequestMapping(value = "/ajax/command")
public class AjaxCommandRestController {

    @RequestMapping(value = "/add")
    public String addEntry(@RequestBody EntryFormDTO entryFormDTO) {
        System.out.println(entryFormDTO.toString());
        return "response";
    }

}
