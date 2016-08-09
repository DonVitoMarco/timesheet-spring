package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.thewalkingcode.model.UserDTO;
import pl.thewalkingcode.service.IRegisterQueryService;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    IRegisterQueryService registerQueryService;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult result, Errors errors) {
        System.out.println(userDTO.getPassword().toUpperCase());
        System.out.println(userDTO.getUsername().toUpperCase());
        registerQueryService.addUser(userDTO);
        return "redirect:/";
    }

}
