package pl.thewalkingcode.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.ShowCriteriaDTO;


@RestController
@RequestMapping(value = "/ajax")
public class AjaxRestController {

    @RequestMapping(value = "/show")
    public String show(@RequestBody ShowCriteriaDTO showCriteriaDTO) {
        System.out.println(showCriteriaDTO.toString());
        return "request";
    }
}