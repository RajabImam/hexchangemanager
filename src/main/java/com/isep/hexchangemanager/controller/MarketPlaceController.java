package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddBookingForm;
import com.isep.hexchangemanager.form.housemanagement.AddHouseForm;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.Image;
import com.isep.hexchangemanager.service.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/marketplace")
@Slf4j
public class MarketPlaceController {
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

    @GetMapping("/search")
    public String getSearch(Model model, @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        @RequestParam("country") String country){

        List<House> houses = new ArrayList<House>();

        if(startDate.isEmpty() && endDate.isEmpty()){
            houses = houseService.houseSearchbyCountry(country);
        }

        else if(endDate.isEmpty() && country.isEmpty()){
            houses = houseService.houseSearchbystartDate(startDate);
        }

        else{
            houses = houseService.houseSearchByDateAndCountry(startDate, endDate, country);
        }

        if(houses.isEmpty()){
            model.addAttribute("status", "No house found");
        }
        else{
            String message = houses.size() + "results found";
            model.addAttribute("status", message);
            model.addAttribute("houseList", houses);
        }
        return "marketplace/searchresult";
    }

    @GetMapping("/details")
    public String getDetails(Model model, @RequestParam("houseId") String houseId, @ModelAttribute AddBookingForm form){
        try{
            //get house by id
            House house = houseService.findById(Long.parseLong(houseId)).get();
            //add constraints
            house.setConstraints(hConstraintService.findHouseConstraint(house));
            //add the services
            house.setServices(hServiceService.findHouseService(house));
            //get available images
            house.setImages(imageService.findHouseImage(house));
            //get availability dates
            house.setAvailabilities(availabilityService.findHouseAvailability(house));

            model.addAttribute("house", house);
        }
        catch(Exception e){
            model.addAttribute("status", "An error occured");
        }

        return "marketplace/details";
    }
}
