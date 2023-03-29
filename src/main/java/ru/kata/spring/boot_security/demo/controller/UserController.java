package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Можно было бы так, но primary key это id, а не name. Тк по заданию нужно перенести все классы ничего не поделаешь)
//    @RequestMapping(value = "/user")
//    public String addUser(ModelMap model, @AuthenticationPrincipal User user) {
//        model.addAttribute("user", user);
//        return "user";
//    }
    //Пришлось добваить метод который ищет по имени  первое совпадение в бд и показывает его на страничке user'а
    @RequestMapping(value = "/user")
    public String addUser(ModelMap model, Principal principal) {
        User user = userService.getFirstUserByName(principal.getName());

        model.addAttribute("user", user);
        return "user";
    }
}