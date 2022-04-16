/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.Role;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.repository.UserRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public void createUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setCreated_on(Date.from(Instant.now()));
        userRepository.save(user);
    }
    
    public void createAdmin(User user){
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
    
    public Optional<User> findOne(Long id){
        return userRepository.findById(id);
    }

    public boolean userExist(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user != null) 
            return true;
        return false;
    }
    
}
