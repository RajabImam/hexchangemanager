/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.model.UserPrincipal;
import com.isep.hexchangemanager.service.UserService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
public class ProfileController {
    
    @Autowired
    private UserService userService;
    
    
//    @GetMapping("/dashboard/index")
//    public String login(Model model, Principal principal){
//        String email = principal.getName();
//        model.addAttribute("user", userService.findByEmail(email));
//        return "/dashboard/index";
//    }
    @GetMapping("/dashboard/index")
    public String login(@AuthenticationPrincipal UserPrincipal loginUser, Model model){
        String email = loginUser.getUsername();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "/dashboard/index";
    }
    
    @GetMapping
    public String loggedInUser(@ModelAttribute("user") BindingResult result, Model model){
        Authentication loginUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loginUser.getName();
        User user = userService.findByEmail(email);
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String gender = user.getGender();
        String phone = user.getPhone();
        String address = user.getAddress();
        model.addAttribute("firstname", firstname);
        model.addAttribute("lastname", lastname);
        model.addAttribute("gender", gender);
        model.addAttribute("phone", phone);
        model.addAttribute("address", address);
        return "/dashboard/user_profile";
    }
    
    @GetMapping("/user_profile")
    public String profileForm(@AuthenticationPrincipal UserPrincipal loginUser, Model model){
        String email = loginUser.getUsername();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "/dashboard/user_profile";
    }
    
    @PostMapping("/user_profile/update")
    public String update(User user, RedirectAttributes redirectAttributes, 
            @AuthenticationPrincipal UserPrincipal loginUser, 
            @RequestParam("profile_img") MultipartFile multipartFile) throws IOException{
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setProfile_img(fileName);
            
            userService.createUser(user);
            String profileFolder = "images/profiles/" + user.getId();
            
            Path path = Paths.get(profileFolder);
            
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            try {
                InputStream inputStream = multipartFile.getInputStream();
                Path filePath = path.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Can't save upload image " + fileName);
            }
            
        }
        userService.createUser(user);
        return "redirect:/user_profile";
    }

}
