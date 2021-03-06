/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.Provider;
import com.isep.hexchangemanager.model.Role;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.model.UserPrincipal;
import com.isep.hexchangemanager.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
    
    public User updateUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        
        return userRepository.save(user);
    }

    public void createAdmin(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean userExist(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user != null)
            return true;
        return false;
    }

    /* Return all users */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // delete user by id
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /*
     * @Override
     * public UserDetails loadUserByUsername(String email) throws
     * UsernameNotFoundException {
     * User user = userRepository.findByEmail(email);
     * if (user == null){
     * throw new UsernameNotFoundException("User not found");
     * }
     * return new
     * org.springframework.security.core.userdetails.User(user.getEmail(),
     * user.getPassword(),
     * mapRolesToAuthorities(user.getRoles()));
     * }
     */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

    /*
     * private Collection<? extends GrantedAuthority>
     * mapRolesToAuthorities(List<Role> roles) {
     * return roles.stream().map(role -> new
     * SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
     * }
     */

    /* Login with OAuth for new account*/
   public void createOAuthUser(String email, String name, Provider provider) {
        User user = new User();
        user.setEmail(email);
        user.setFirstname(name);
        user.setCreatedDate(new Date());
        user.setProvider(provider);
        
        userRepository.save(user);
    }
    
    /* Login with OAuth for existing account*/
    public void updateOAuthUser(User user, String name, Provider provider) {
        user.setFirstname(name);
        user.setProvider(provider);
        
        userRepository.save(user);
    }

}
