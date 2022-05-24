/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.Images;
import com.isep.hexchangemanager.repository.ImagesRepository;
import com.isep.hexchangemanager.service.ImageService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class ImageServiceImpl implements ImageService{
    
    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    public Images addImage(Images images) {
        return imagesRepository.save(images);
    }

    @Override
    public List<Images> getImages() {
        return imagesRepository.findAll();
    }

    @Override
    public Optional<Images> findById(int id) {
        return imagesRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        imagesRepository.deleteById(id);
    }

    @Override
    public List<Images> findHouseImage(House house) {
        return imagesRepository.findByHouse(house);
    }
    
}
