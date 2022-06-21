/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.Image;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */

public interface IImageService {
    
    Image addImage(Image images);

    // get all constraints
    List<Image> getImages();

    Optional<Image> findById(int id);

    // delete constraint by id
    void delete(int id);

    //find all constraints on a particular house
    List<Image> findHouseImage(House house);
}
