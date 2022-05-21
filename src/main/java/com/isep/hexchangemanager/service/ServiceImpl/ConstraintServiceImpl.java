/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.Constraint;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.repository.ConstraintRepository;
import com.isep.hexchangemanager.service.IConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class ConstraintServiceImpl implements IConstraintService {
    @Autowired
    private ConstraintRepository constraintRepository;

    @Override
    public void addConstraint(Constraint constraint) {
        constraintRepository.save(constraint);
    }

    @Override
    public List<Constraint> getConstraints() {
        return constraintRepository.findAll();
    }

    @Override
    public Optional<Constraint> findById(int id) {
        return constraintRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        constraintRepository.deleteById(id);
    }

    @Override
    public List<Constraint> findHouseConstraint(House house) {
        return constraintRepository.findByHouse(house);
    }
}
