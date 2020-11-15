package ar.edu.unnoba.poo2020.ProyectoMaven.controller;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.User;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String userNew(Model model){

        model.addAttribute("user", new User());
        return "Users/new";

    }
   /* @PostMapping
    public String create(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/Users";
    }
    */

    @PostMapping
    public String create(@ModelAttribute User user){
        if(userService.create(user)) {
            return "redirect:/Users/bienvenido";
        }else{
            return "redirect:/Users/new";
        }
    }

    @GetMapping("/bienvenido")
    public String userBienvenido(Model model){
        model.addAttribute("user", new User());
        return "Users/bienvenido";
    }

}


