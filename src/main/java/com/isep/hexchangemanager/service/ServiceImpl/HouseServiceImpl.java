/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.repository.HouseRepository;

import java.util.List;
import java.util.Optional;

import com.isep.hexchangemanager.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class HouseServiceImpl implements IHouseService {
    @Autowired
    private HouseRepository houseRepository;

    public void addHouse(House house) {
        houseRepository.save(house);
    }

    // get all houses
    public List<House> getHouses() {
        return houseRepository.findAll();
    }

    // find house by id
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    // delete house by id
    public void delete(Long id) {
        houseRepository.deleteById(id);
    }

    public List<House> findUserHouse(User user) {
        return houseRepository.findByUser(user);
    }

}
