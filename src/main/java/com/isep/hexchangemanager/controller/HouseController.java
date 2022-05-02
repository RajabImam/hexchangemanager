/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.service.HouseService;
import com.isep.hexchangemanager.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;
    
    @PostMapping("/addHouse")
    public String createHouse(House house, HttpSession session){
       
        String email = (String) session.getAttribute("email");
        houseService.addHouse(house, userService.findByEmail(email));
        return "redirect:/dashboard/add_house";
    }
    
    @GetMapping("/viewHouse")
    public String houseList(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("house", houseService.findUserHouse(user));
        //model.addAttribute("house", houseService.findById(1L));
        return "dashboard/add_house";
    }
}
