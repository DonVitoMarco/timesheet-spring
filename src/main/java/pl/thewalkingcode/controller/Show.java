package pl.thewalkingcode.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.thewalkingcode.model.SearchCriteria;


@RestController
public class Show {

    @RequestMapping(value = "/ajax/show")
    public String show(@RequestBody SearchCriteria searchCriteria) {
        System.out.println("SHOW");
        System.out.println(searchCriteria.getEnd());
        return "request";
    }

}
