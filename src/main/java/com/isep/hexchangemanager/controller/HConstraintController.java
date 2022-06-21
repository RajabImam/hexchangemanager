/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddConstraintForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.HConstraint;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.service.IHConstraintService;
import com.isep.hexchangemanager.service.IHouseService;
import com.isep.hexchangemanager.util.Message;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
@RequestMapping("/constraint")
@Slf4j
public class HConstraintController {
    @Autowired
    private IHConstraintService constraintService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ModelMapper modelMapper ;

    /** Display the add house screen */
    @GetMapping ("/add" )
    public String getAddHouse(Model model, @ModelAttribute AddConstraintForm form ) {
        return "house/list" ;
    }

    /** add house process */
    @PostMapping ("/add" )
    public String postAddConstraint(Model model , Principal principal,
                               @ModelAttribute @Validated(GroupOrder.class ) AddConstraintForm form ,
                               BindingResult bindingResult, HttpSession session) {
        // Input check result
        if (bindingResult.hasErrors()) {
        // NG: Return to the user signup screen
            return getAddHouse(model , form);
        }
        log.info(form .toString());

        //get house id from the form
        Long houseId = form.getHouseId();

        //map form details to the house model
        HConstraint constraint = modelMapper.map(form, HConstraint.class);

        try{
        //get house details using the houseId
        House house = houseService.findById(houseId).get();

        //set the house field of the house model
        constraint.setHouse(house);

        //add constraint to database
            constraintService.addConstraint(constraint);
            model.addAttribute("status", "1");
            model.addAttribute("message", "Constraint added successfully");
            session.setAttribute("message", new Message("Constraint added successfully", "success"));
        }
        catch (Exception e){
            model.addAttribute("status", "0");
            model.addAttribute("message", "Constraint added not successful");
            session.setAttribute("message", new Message("Constraint not added. Try again", "danger"));
            log.error(e.getMessage());
        }

        // Redirect to Dashboard
        return "redirect:/house/list" ;
    }
    
    @GetMapping("/list/{id}")
    public String houseList(Model model,  @PathVariable("userId" ) Long id){
        House house;
        List<HConstraint> constraintList = new ArrayList<>();
        Optional<House> result = houseService.findById(id);

        if(result.isPresent()){
            house = result.get();
            constraintList = constraintService.findHouseConstraint(house);
        }

        model .addAttribute("constraintList" , constraintList );

        return "house/list";
    }
}
