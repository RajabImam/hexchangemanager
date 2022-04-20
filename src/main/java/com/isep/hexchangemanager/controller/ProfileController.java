/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
public class ProfileController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal){
        
        String email = principal.getName();
        model.addAttribute("profile", userService.findByEmail(email));
        return "profile";
    }
}
