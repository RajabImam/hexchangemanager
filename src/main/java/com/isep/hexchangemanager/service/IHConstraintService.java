package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.HConstraint;
import com.isep.hexchangemanager.model.House;

import java.util.List;
import java.util.Optional;

public interface IHConstraintService {
    void addConstraint(HConstraint constraint);

    // get all constraints
    List<HConstraint> getConstraints();

    Optional<HConstraint> findById(int id);

    // delete constraint by id
    void delete(int id);

    //find all constraints on a particular house
    List<HConstraint> findHouseConstraint(House house);
}
