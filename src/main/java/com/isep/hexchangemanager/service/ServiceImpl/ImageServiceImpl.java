/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.Image;
import com.isep.hexchangemanager.repository.ImagesRepository;
import com.isep.hexchangemanager.service.IImageService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class ImageServiceImpl implements IImageService {
    
    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    public Image addImage(Image images) {
        return imagesRepository.save(images);
    }

    @Override
    public List<Image> getImages() {
        return imagesRepository.findAll();
    }

    @Override
    public Optional<Image> findById(int id) {
        return imagesRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        imagesRepository.deleteById(id);
    }

    @Override
    public List<Image> findHouseImage(House house) {
        return imagesRepository.findByHouse(house);
    }
    
}
