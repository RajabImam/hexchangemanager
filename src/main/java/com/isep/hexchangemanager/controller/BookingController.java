/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddBookingForm;
import com.isep.hexchangemanager.form.housemanagement.AddHouseForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.*;
import com.isep.hexchangemanager.service.IAvailabilityService;
import com.isep.hexchangemanager.service.IBookingService;
import com.isep.hexchangemanager.service.IHouseService;
import com.isep.hexchangemanager.service.UserService;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.isep.hexchangemanager.util.BookingStatus;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
@RequestMapping("/booking")
@Slf4j
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private IHouseService houseService;
    @Autowired
    private IAvailabilityService availabilityService;
    @Autowired
    private ModelMapper modelMapper ;

    /*@GetMapping("/add")
    public String getBookingForm(Model model){
        model.addAttribute("booking", new Booking());
        return "booking/list";
    }*/
    
    @GetMapping("/add")
    public String addBooking(Model model , Principal principal,
                             @ModelAttribute @Validated(GroupOrder.class ) AddBookingForm form,
                             BindingResult bindingResult){

        // Input check result
        if (bindingResult.hasErrors()) {
            // NG: Return to the user signup screen
            return "dashboard/dashboard";
        }
        log.info(form .toString());

        //get house id from the form
        Long houseId = Long.parseLong(form.getHouseId());
        //get availability id from the form
        Long availabilityId = Long.parseLong(form.getHouseId());

        //map form details to the house model
        //Booking booking = modelMapper.map(form, Booking.class);

        //create new booking object
        Booking booking = new Booking();

        try{
            //get house details using the houseId
            House house = houseService.findById(houseId).get();

            //get the user performing the booking
            User user = userService.findByEmail(principal.getName());

            //set the house field of the house model
            Availability availability = availabilityService.findById(availabilityId).get();

            //get start and End date from availability
            Date startDate = availability.getStartDate();
            Date endDate = availability.getEndDate();

            //update the fields of the booking class
            booking.setHouse(house);
            booking.setUser(user);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);

            //set status to 0 - Pending
            //set status to 1 - Approved
            //set status to 2 - Disapproved
            //set status to 3 - Cancelled

            booking.setStatus(0);
            booking.setBookingDate(new Date());

            bookingService.save(booking);
            model.addAttribute("status", "1");
            model.addAttribute("message", "Booking added successfully");
        }
        catch (Exception e){
            model.addAttribute("status", "0");
            model.addAttribute("message", "Booking not successful");
            log.error(e.getMessage());
        }
        return "booking/list";
    }

    @GetMapping("/list")
    public String bookingList(Model model, Principal principal){
        try{
            String email = principal.getName();
            User user = userService.findByEmail(email);

            //Get house list
            List<Booking> bookingList = bookingService.findUserBooking(user);
            //Registered in Model
            model .addAttribute("bookingList" , bookingList );
        }
        catch(Exception e){

        }
        return "booking/list";
    }

    @GetMapping("/request")
    public String bookingRequest(Model model, Principal principal){
        try{
            String email = principal.getName();
            User user = userService.findByEmail(email);

            //Get house list
            List<Booking> bookingList = bookingService.findUserBooking(user);
            //Registered in Model
            model .addAttribute("bookingList" , bookingList );
        }
        catch(Exception e){

        }
        return "booking/bookingrequest";
    }
    
    @PostMapping("/delete")
    public String delete(Model model){
        return "booking/list";
    }
}

