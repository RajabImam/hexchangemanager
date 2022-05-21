/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.model.Booking;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.model.UserPrincipal;
import com.isep.hexchangemanager.service.BookingService;
import com.isep.hexchangemanager.service.IHouseService;
import com.isep.hexchangemanager.service.UserService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
@RequestMapping("/booking")
@Slf4j
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private IHouseService houseService;
    
    
    @GetMapping("/add")
    public String getBookingForm(Model model){
        
        model.addAttribute("booking", new Booking());
        return "booking/addbooking";
    }
    
    @GetMapping("/list")
    public String bookingList(){
        return "booking/list";
    }
    
    @GetMapping("/bookingrequest")
    public String bookingRequest(){
        return "booking/bookingrequest";
    }
    
    @PostMapping("/add")
    public String addNew(Booking booking, @AuthenticationPrincipal UserPrincipal loginUser, User user, House house){
        String email = loginUser.getUsername();
        user = userService.findByEmail(email);
        house = (House) houseService.findUserHouse(user);
        bookingService.addBooking(booking, user, house);
        return "redirect:/booking/list";
    }
    
    @RequestMapping("bookings/findById")
    @ResponseBody
    public Optional<Booking> findById(int id){
        return bookingService.findById(id);
    }
    
    @RequestMapping(value = "/bookings/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Booking booking){
        bookingService.save(booking);
        return "redirect:/dashboard/booking";
    }
    
    @RequestMapping(value = "/bookings/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id){
        bookingService.delete(id);
        return "redirect:/dashboard/booking";
    }
    
}

