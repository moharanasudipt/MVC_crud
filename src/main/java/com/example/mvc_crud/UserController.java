package com.example.mvc_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private  UserService userService;
    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/users")
    public  String  listAll(Model model){
      List<User> users = userService.findAll();
      model.addAttribute("listUsers",users);
        return "users";
    }

    @GetMapping("/users/new")
    public  String saveDate(Model model){
        model.addAttribute("user",new User());
        return "user_form";
    }

    @PostMapping("/users/save")
    public  String postData(User user){
        userService.save(user);
        return "redirect:/users";
    }
}
