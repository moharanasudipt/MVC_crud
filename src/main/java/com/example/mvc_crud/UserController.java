package com.example.mvc_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("pageTitle","Add new User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public  String postData(User user , RedirectAttributes ra){
        userService.save(user);
        ra.addFlashAttribute("message","The User has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public  String editUser(@PathVariable("id") Integer id,Model model,RedirectAttributes ra){
       try {
           User user=userService.get(id);
           model.addAttribute("user",user);
           model.addAttribute("pageTitle","Edit User with id ("+id+")");
           model.addAttribute("message","User data successfully updated.");
           return "user_form";
       }
       catch (UserNotFoundexception e){
           model.addAttribute("message","User failed to updated his data");
           return "redirect:/users";
       }
    }



}
