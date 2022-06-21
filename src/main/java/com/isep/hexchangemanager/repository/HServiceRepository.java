/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.repository;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.HService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author RAJAB IMAM
 */
@Repository
public interface HServiceRepository extends JpaRepository<HService, Integer>{
    public List<HService> findByHouse(House house);
}
