/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddHouseForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.HConstraint;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.service.*;
import com.isep.hexchangemanager.util.Message;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
@RequestMapping("/house")
@Slf4j
public class HouseController {
    @Autowired
    private IHouseService houseService;

    @Autowired
    private IHConstraintService hConstraintService;

    @Autowired
    private IHServiceService hServiceService;

    @Autowired
    private IAvailabilityService availabilityService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper ;
    
    

    /** Display the add house screen */
    @GetMapping ("/add" )
    public String getAddHouse(Model model, @ModelAttribute AddHouseForm form ) {
        return "house/addhouse" ;
    }

    /** add house process */
    @PostMapping ("/add" )
    public String postAddHouse(Model model , Principal principal,
                               @ModelAttribute @Validated(GroupOrder.class ) AddHouseForm form,
                               BindingResult bindingResult, HttpSession session) {
        // Input check result
        if (bindingResult.hasErrors()) {
        // NG: Return to the user signup screen
            return getAddHouse(model , form);
        }
        log.info(form .toString());

        //map form details to the house model
        House house = modelMapper.map(form, House.class);

        //get user email from spring security
        String email = principal.getName();

        //get user details using the email
        User user = userService.findByEmail(email);

        //set the user field of the house model
        house.setUser(user);

        //try to add house to database
        try{
            houseService.addHouse(house);
            model.addAttribute("status", "1");
            model.addAttribute("message", "House added successfully");
            session.setAttribute("message", new Message("House added successfully", "success"));
        }
        catch (Exception e){
            model.addAttribute("status", "0");
            model.addAttribute("message", "House added not successful");
            session.setAttribute("message", new Message("House not added. Try again", "danger"));
            log.error(e.getMessage());
        }

        // Redirect to Dashboard
        return "redirect:/house/list" ;
    }
    
    @GetMapping("/list")
    //public String houseList(Model model, Principal principal, @ModelAttribute AddConstraintForm form)
    public String houseList(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("house", houseService.findUserHouse(user));
        //Get house list
        List<House> houseList = houseService.findUserHouse(user);
        //Registered in Model
        model .addAttribute("houseList" , houseList );
        //model.addAttribute("status", null);
        return "house/list";
    }
    
    @GetMapping("/detail")
    public String houseDetails(Model model, @RequestParam("houseId") String houseId){
        try{
           //get house by id
            House house = houseService.findById(Long.parseLong(houseId)).get();
            //add constraints
            house.setConstraints(hConstraintService.findHouseConstraint(house));
            //add the services
            house.setServices(hServiceService.findHouseService(house));
            //get availability images
            house.setImages(imageService.findHouseImage(house));
            //get availability dates
            house.setAvailabilities(availabilityService.findHouseAvailability(house));

            model.addAttribute("house", house);
        }
        catch(Exception e){
            model.addAttribute("status", "An error occured");
        }
        return "house/detail";
    }
}
