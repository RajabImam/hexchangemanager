/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.HService;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.repository.ServiceRepository;
import com.isep.hexchangemanager.service.IHServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */

@Service
public class HServiceServiceImpl implements IHServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public void addService(HService service) {
        serviceRepository.save(service);
    }

    @Override
    public List<HService> getServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<HService> findById(int id) {
        return serviceRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<HService> findHouseService(House house) {
        return serviceRepository.findByHouse(house);
    }
}
