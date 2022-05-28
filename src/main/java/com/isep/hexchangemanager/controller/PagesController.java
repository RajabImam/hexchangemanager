/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.model.Booking;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
public class PagesController {
    
    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
    
    @GetMapping("/about")
    public String aboutPage(){
        return "/hm/about";
    }
    
    @GetMapping("/discovery")
    public String discoveryPage(){
        return "/hm/discovery";
    }
    
    @GetMapping("/faqs")
    public String faqsPage(){
        return "/hm/faqs";
    }
    
    @GetMapping("/contact")
    public String contactPage(){
        return "/hm/contact";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "/account/login";
    }
    
    /*@GetMapping("/dashboard")
    public String dashboardPage(){
        return "/dashboard/dashboard";
    }*/
    
    /*@GetMapping("/add_house")
    public String createHouseForm(String email, Model model, HttpSession session){
        session.setAttribute("email", email);
        model.addAttribute("house", new House());
        return "/house/add_house";
    }*/
    
     /*@GetMapping("/booking")
    public String createBookingForm(String email, Model model, HttpSession session){
        session.setAttribute("email", email);
        model.addAttribute("booking", new Booking());
        return "/booking/booking";
    }*/
     
//    @GetMapping("/user_profile")
//    public String profileForm(String email, Model model, HttpSession session){
//        session.setAttribute("email", email);
//        model.addAttribute("profile", new User());
//        return "/dashboard/user_profile";
//    }
    
}
