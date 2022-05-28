/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.businessService.IAdvertise;
import com.isep.hexchangemanager.form.housemanagement.AddAvailabilityForm;
import com.isep.hexchangemanager.form.housemanagement.AddServiceForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.Availability;
import com.isep.hexchangemanager.model.HService;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.service.*;
import com.isep.hexchangemanager.util.StringToLocalDate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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
    private IHConstraintService hConstraintService;

    @Autowired
    private IHServiceService hServiceService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private IAdvertise advertise;

    @Autowired
    private StringToLocalDate dateConverter;

    /** Display the add house screen */
    @GetMapping ("/add" )
    public String getAddAvailability(Model model, @ModelAttribute AddAvailabilityForm form ) {
        return "house/list" ;
    }

    /** add house process */
    @PostMapping ("/add" )
    public String postAddAvailability(Model model , Principal principal,
                               @ModelAttribute @Validated(GroupOrder.class ) AddAvailabilityForm form ,
                               BindingResult bindingResult ) {
        // Input check result
        if (bindingResult.hasErrors()) {
        // NG: Return to the user signup screen
            return "redirect:/house/list";
        }
        log.info(form .toString());

        try{
            //map form details to the house model
            Availability availability = modelMapper.map(form, Availability.class);

        //get house id from the form
        Long houseId = form.getHouseId();

        //get house details using the houseId
        House house = houseService.findById(houseId).get();

        //Check if house is eligible for advertisement
            HashMap<Integer,String> result = advertise.checkAdvertiseEligibility(availabilityService.findHouseAvailability(house),
                                                        hConstraintService.findHouseConstraint(house),
                                                        hServiceService.findHouseService(house),
                                                        imageService.findHouseImage(house),
                                                        availability.getStartDate(),
                                                        availability.getEndDate());

         //advertise if house is eligible                                               availability.getEndDate());
        if(result.containsKey(1)){
            //set the house field of the house model
            availability.setHouse(house);

            //add availability to database
            availabilityService.addAvailability(availability);
            model.addAttribute("status", "1");
            model.addAttribute("message", "Availability added successfully");
        }

        //else return error message
        else{
            model.addAttribute("status", "0");
            model.addAttribute("message", result.get(0));
        }
        }
        catch (Exception e){
            model.addAttribute("status", "Service added not successful");
            log.error(e.getMessage());
        }

        // Redirect to house list
        return "redirect:/house/list" ;
    }
    
    @GetMapping("/list/{id}")
    public String houseList(Model model,  @PathVariable("userId" ) Long id){
        House house;
        List<Availability> availabilityList = new ArrayList<>();
        Optional<House> result = houseService.findById(id);

        if(result.isPresent()){
            house = result.get();
            availabilityList = availabilityService.findHouseAvailability(house);
        }

        model .addAttribute("constraintList" , availabilityList );

        return "house/list";
    }
}
