package com.example.mvc_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
