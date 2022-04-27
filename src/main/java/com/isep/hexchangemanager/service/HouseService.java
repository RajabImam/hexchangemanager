/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.repository.HouseRepository;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class HouseService {
    @Autowired
    private HouseRepository houseRepository;
    
    public void addHouse(House house, User user){
        house.setUser(user);
        house.setStatus(0);
        houseRepository.save(house);
    }
    
    public List<House> findUserHouse(User user){
        return houseRepository.findByUser(user);
    }
    
    public List<House> getHouses(){
        return houseRepository.findAll();
    }
    
      //find booking by id
    public Optional<House> findById(Long id){
        return houseRepository.findById(id);
    }
    
    //delete booking by id 
    public void delete(Long id){
        houseRepository.deleteById(id);
    }
    
}
