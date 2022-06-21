package com.isep.hexchangemanager.businessService;

import com.isep.hexchangemanager.model.Availability;
import com.isep.hexchangemanager.model.HConstraint;
import com.isep.hexchangemanager.model.HService;
import com.isep.hexchangemanager.model.Image;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IAdvertise {
    HashMap<Integer, String> checkAdvertiseEligibility(List<Availability> availabilities,
                                                       List<HConstraint> hconstraints,
                                                       List<HService> hServices,
                                                       List<Image> images,
                                                       Date startDate,
                                                       Date endDate);
}
