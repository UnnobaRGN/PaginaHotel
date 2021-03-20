package ar.edu.unnoba.poo2020.ProyectoMaven.controller;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.User;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Users")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController (IUserService userService){

        this.userService = userService;

    }

    @GetMapping("/new")
    public String userNew(Model model, Authentication auth){
        model.addAttribute("user", new User());
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
        }
        return "Users/new";

    }


   /* @PostMapping
    public String create(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/Users";
    }
    */

    @PostMapping
    public String create(@ModelAttribute User user, Model model, Authentication auth){
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
        }
        if(userService.create(user)) {
            return "redirect:/Users";
        }else{
            //model.addAttribute("texto", "ya existe un usuario con ese email");
            model.addAttribute("mensaje","");
            return "Users/new";
        }
    }

    @GetMapping
    public String userBienvenido(Model model,Authentication auth){
        model.addAttribute("user", new User());
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
        }
        return "/Users/index";
    }

}


