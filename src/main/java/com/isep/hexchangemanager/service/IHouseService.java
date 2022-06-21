package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;

import java.util.List;
import java.util.Optional;

public interface IHouseService {
    void addHouse(House house);

    // get all houses
    List<House> getHouses();

    // find house by id
    Optional<House> findById(Long id);

    // delete house by id
    void delete(Long id);

    List<House> findUserHouse(User user);

    List<House> houseSearchByDateAndCountry(String startDate, String endDate, String country);

    List<House> houseSearchbystartDate(String startDate);

    List<House> houseSearchbyCountry(String country);
}