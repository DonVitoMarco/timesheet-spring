package pl.thewalkingcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.thewalkingcode.model.UserRegisterDTO;
import pl.thewalkingcode.service.IUserCommandService;
import pl.thewalkingcode.service.IUserQueryService;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegisterController {

    private IUserCommandService userCommandService;
    private IUserQueryService userQueryService;

    @Autowired
    public RegisterController(IUserCommandService userCommandService, IUserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        model.addAttribute("user", userRegisterDTO);
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute("user") @Valid UserRegisterDTO userRegisterDTO, BindingResult result, Errors errors) {
        System.out.println(userRegisterDTO.toString());

        if(result.hasErrors()) return "redirect:register?error";
        if(userQueryService.checkExistUsername(userRegisterDTO.getUsername()))
            return "redirect:register?validUsername";

        userCommandService.registerUser(userRegisterDTO);
        return "redirect:/";
    }

}
