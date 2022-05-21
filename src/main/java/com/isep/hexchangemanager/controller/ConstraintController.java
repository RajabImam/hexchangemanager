/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddConstraintForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.Constraint;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.service.IConstraintService;
import com.isep.hexchangemanager.service.IHouseService;
import com.isep.hexchangemanager.service.UserService;
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
@RequestMapping("/constraint")
@Slf4j
public class ConstraintController {
    @Autowired
    private IConstraintService constraintService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ModelMapper modelMapper ;

    /** Display the add house screen */
    @GetMapping ("/add" )
    public String getAddHouse(Model model, @ModelAttribute AddConstraintForm form ) {
        return "house/addhouse" ;
    }

    /** add house process */
    @PostMapping ("/add" )
    public String postAddConstraint(Model model , Principal principal,
                               @ModelAttribute @Validated(GroupOrder.class ) AddConstraintForm form ,
                               BindingResult bindingResult ) {
        // Input check result
        if (bindingResult.hasErrors()) {
        // NG: Return to the user signup screen
            return getAddHouse(model , form);
        }
        log.info(form .toString());

        //get house id from the form
        Long houseId = form.getHouseId();

        //map form details to the house model
        Constraint constraint = modelMapper.map(form, Constraint.class);

        try{
        //get house details using the houseId
        Optional<House> result = houseService.findById(houseId);
        House house = result.get();

        //set the house field of the house model
        constraint.setHouse(house);


        //try to add constraint to database
            constraintService.addConstraint(constraint);
            model.addAttribute("status", "Constraint added successfully");
        }
        catch (Exception e){
            model.addAttribute("status", "Constraint added not successful");
            log.error(e.getMessage());
        }

        // Redirect to Dashboard
        return "redirect:/house/list" ;
    }
    
    @GetMapping("/list/{id}")
    public String houseList(Model model,  @PathVariable("userId" ) Long id){
        House house;
        List<Constraint> constraintList = new ArrayList<>();
        Optional<House> result = houseService.findById(id);

        if(result.isPresent()){
            house = result.get();
            constraintList = constraintService.findHouseConstraint(house);
        }

        model .addAttribute("constraintList" , constraintList );

        return "house/list";
    }
}
