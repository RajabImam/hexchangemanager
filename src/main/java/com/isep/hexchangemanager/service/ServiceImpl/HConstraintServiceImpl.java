/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.HConstraint;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.repository.HConstraintRepository;
import com.isep.hexchangemanager.service.IHConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class HConstraintServiceImpl implements IHConstraintService {
    @Autowired
    private HConstraintRepository constraintRepository;

    @Override
    public void addConstraint(HConstraint constraint) {
        constraintRepository.save(constraint);
    }

    @Override
    public List<HConstraint> getConstraints() {
        return constraintRepository.findAll();
    }

    @Override
    public Optional<HConstraint> findById(int id) {
        return constraintRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        constraintRepository.deleteById(id);
    }

    @Override
    public List<HConstraint> findHouseConstraint(House house) {
        return constraintRepository.findByHouse(house);
    }
}
