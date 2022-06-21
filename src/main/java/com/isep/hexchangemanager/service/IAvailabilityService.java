package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.Availability;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;

import java.util.List;
import java.util.Optional;

public interface IAvailabilityService {
    void addAvailability(Availability availability);

    // get all Availabilities
    List<Availability> getAvailabilities();

    // find availability by id
    Optional<Availability> findById(Long id);

    // delete availability by id
    void delete(Long id);

    List<Availability> findHouseAvailability(House house);
}