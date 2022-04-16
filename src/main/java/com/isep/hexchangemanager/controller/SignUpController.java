/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
public class SignUpController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/signup")
    public String createAccountForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String createUserAccount(@Validated User user, BindingResult bindingResult, Model model){
        
       /* if (bindingResult.hasErrors()) {
            return "signup";
        }if (userService.userExist(user.getId())) {
            model.addAttribute("exist",true);
            return "signup";
        }*/
        userService.createUser(user);
        return "success";
    }
}
