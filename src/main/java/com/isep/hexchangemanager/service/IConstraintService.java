package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.Constraint;
import com.isep.hexchangemanager.model.House;

import java.util.List;
import java.util.Optional;

public interface IConstraintService {
    void addConstraint(Constraint constraint);

    // get all constraints
    List<Constraint> getConstraints();

    Optional<Constraint> findById(int id);

    // delete constraint by id
    void delete(int id);

    //find all constraints on a particular house
    List<Constraint> findHouseConstraint(House house);
}
