/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddConstraintForm;
import com.isep.hexchangemanager.form.housemanagement.AddServiceForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.Constraint;
import com.isep.hexchangemanager.model.HService;
import com.isep.hexchangemanager.model.House;
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
@RequestMapping("/service")
@Slf4j
public class HServiceController {
    @Autowired
    private IHServiceService serviceService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ModelMapper modelMapper ;

    /** Display the add house screen */
    @GetMapping ("/add" )
    public String getAddHouse(Model model, @ModelAttribute AddServiceForm form ) {
        return "house/addhouse" ;
    }

    /** add house process */
    @PostMapping ("/add" )
    public String postAddService(Model model , Principal principal,
                               @ModelAttribute @Validated(GroupOrder.class ) AddServiceForm form ,
                               BindingResult bindingResult ) {
        // Input check result
        if (bindingResult.hasErrors()) {
        // NG: Return to the user signup screen
            return getAddHouse(model , form);
        }
        log.info(form .toString());

        //map form details to the house model
        HService service = modelMapper.map(form, HService.class);

        //get house id from the form
        Long houseId = service.getId();

        try{
        //get house details using the houseId
        Optional<House> result = houseService.findById(houseId);
        House house = result.get();

        //set the user field of the house model
        service.setHouse(house);

        //try to add house to database

            serviceService.addService(service);
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
        List<HService> serviceList = new ArrayList<>();
        Optional<House> result = houseService.findById(id);

        if(result.isPresent()){
            house = result.get();
            serviceList = serviceService.findHouseService(house);
        }

        model .addAttribute("constraintList" , serviceList );

        return "house/list";
    }
}
