/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.repository;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RAJAB IMAM
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Long>{

    public List<House> findByUser(User user);
    
}
