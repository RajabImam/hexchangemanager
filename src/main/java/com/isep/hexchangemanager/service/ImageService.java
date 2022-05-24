/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.Images;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */

public interface ImageService {
    
    Images addImage(Images images);

    // get all constraints
    List<Images> getImages();

    Optional<Images> findById(int id);

    // delete constraint by id
    void delete(int id);

    //find all constraints on a particular house
    List<Images> findHouseImage(House house);
}
