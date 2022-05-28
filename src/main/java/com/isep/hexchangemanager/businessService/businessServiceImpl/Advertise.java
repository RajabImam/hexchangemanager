package com.isep.hexchangemanager.businessService.businessServiceImpl;

import com.isep.hexchangemanager.businessService.IAdvertise;
import com.isep.hexchangemanager.model.Availability;
import com.isep.hexchangemanager.model.HConstraint;
import com.isep.hexchangemanager.model.HService;
import com.isep.hexchangemanager.model.Image;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class Advertise implements IAdvertise {
    @Override
    public HashMap<Integer, String> checkAdvertiseEligibility(List<Availability> availabilities,
                                                                     List<HConstraint> hconstraints,
                                                                     List<HService> hServices,
                                                                     List<Image> images,
                                                                     Date startDate,
                                                                     Date endDate){
        HashMap<Integer, String> response = new HashMap<>();
        int status = 1;
        String message = "";

        if(hconstraints.size() < 1 ||
                hServices.size()< 1||
                hconstraints.size()<1||
                images.size()< 3){
            status = 0;
            message += "House must have at least one(1) constraint/service and at least three(3) images\n";
        }

        for (Availability availability : availabilities){
            if(startDate.compareTo(availability.getStartDate()) == 0){
                status = 0;
                message += "This property is already advertised for selected Start Date";
            }
            if(endDate.compareTo(availability.getEndDate()) == 0){
                status = 0;
                message += "This property is already advertised for selected End Date";
            }
        }

        response.put(status, message);
        return response;
    }
}
