/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.repository;

import com.isep.hexchangemanager.model.Constraint;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author RAJAB IMAM
 */
@Repository
public interface ConstraintRepository extends JpaRepository<Constraint, Integer> {
    public List<Constraint> findByHouse(House house);
}
