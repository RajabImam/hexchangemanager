/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.Availability;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.repository.AvailabilityRepository;
import com.isep.hexchangemanager.repository.HouseRepository;
import com.isep.hexchangemanager.service.IAvailabilityService;
import com.isep.hexchangemanager.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class AvailabilityServiceImpl implements IAvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public void addAvailability(Availability availability) {
        availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> getAvailabilities() {
        return availabilityRepository.findAll();
    }

    // delete house by id
    public void delete(Long id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    public List<Availability> findHouseAvailability(House house) {
        return availabilityRepository.findByHouse(house);
    }

    @Override
    public Optional<Availability> findById(Long id) {
        return availabilityRepository.findById(id);
    }
}
