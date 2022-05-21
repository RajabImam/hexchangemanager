/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddAvailabilityForm;
import com.isep.hexchangemanager.form.housemanagement.AddServiceForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.Availability;
import com.isep.hexchangemanager.model.HService;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.service.IAvailabilityService;
import com.isep.hexchangemanager.service.IHServiceService;
import com.isep.hexchangemanager.service.IHouseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
@RequestMapping("/availability")
@Slf4j
public class AvailabilityController {
    @Autowired
    private IAvailabilityService availabilityService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ModelMapper modelMapper ;

    /** Display the add house screen */
    @GetMapping ("/add" )
    public String getAddAvailability(Model model, @ModelAttribute AddAvailabilityForm form ) {
        return "house/list" ;
    }

    /** add house process */
    @PostMapping ("/add" )
    public String postAddService(Model model , Principal principal,
                               @ModelAttribute @Validated(GroupOrder.class ) AddAvailabilityForm form ,
                               BindingResult bindingResult ) {
        // Input check result
        if (bindingResult.hasErrors()) {
        // NG: Return to the user signup screen
            return "redirect:/house/list";
        }
        log.info(form .toString());

        //map form details to the house model
        Availability availability = modelMapper.map(form, Availability.class);

        //get house id from the form
        Long houseId = availability.getId();

        try{
        //get house details using the houseId
        Optional<House> result = houseService.findById(houseId);
        House house = result.get();

        //set the user field of the house model
        availability.setHouse(house);

        //try to add availability to database

            availabilityService.addAvailability(availability);
            model.addAttribute("status", "Service added successfully");
        }
        catch (Exception e){
            model.addAttribute("status", "Service added not successful");
            log.error(e.getMessage());
        }

        // Redirect to Dashboard
        return "redirect:/house/list" ;
    }
    
    @GetMapping("/list/{id}")
    public String houseList(Model model,  @PathVariable("userId" ) Long id){
        House house;
        List<Availability> availabilityList = new ArrayList<>();
        Optional<House> result = houseService.findById(id);

        if(result.isPresent()){
            house = result.get();
            availabilityList = availabilityService.findAvailabilityHouse(house);
        }

        model .addAttribute("constraintList" , availabilityList );

        return "house/list";
    }
}