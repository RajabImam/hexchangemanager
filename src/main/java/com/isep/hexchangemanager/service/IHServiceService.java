package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.HService;
import com.isep.hexchangemanager.model.House;

import java.util.List;
import java.util.Optional;

public interface IHServiceService {
    void addService(HService service);

    // get all services
    List<HService> getServices();

    Optional<HService> findById(int id);

    // delete service by id
    void delete(int id);

    //find all services on a particular house
    List<HService> findHouseService(House house);
}
