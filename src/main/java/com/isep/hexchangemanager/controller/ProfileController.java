/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.model.UserPrincipal;
import com.isep.hexchangemanager.oauth.CustomOAuth2User;
import com.isep.hexchangemanager.repository.UserRepository;
import com.isep.hexchangemanager.service.UserService;
import com.isep.hexchangemanager.util.FileUploadUtility;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    
//    @GetMapping("/dashboard/index")
//    public String login(Model model, Principal principal){
//        String email = principal.getName();
//        model.addAttribute("user", userService.findByEmail(email));
//        return "/dashboard/index";
//    }
    @GetMapping("/dashboard")
    public String login(@AuthenticationPrincipal UserPrincipal loginUser, Model model){
        String email = loginUser.getUsername();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "/dashboard/dashboard";
    }
    
    @GetMapping("/oauth2/authorization/google")
    public String oAuthLogin(@AuthenticationPrincipal CustomOAuth2User oAuthUser, Model model){
        String email = oAuthUser.getEmail();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "/dashboard/dashboard";
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
        return "/user/user_profile";
    }
    
    @PostMapping("/user_profile/update")
    public String update(User user, RedirectAttributes redirectAttributes, 
            @AuthenticationPrincipal UserPrincipal loginUser, 
            @RequestParam("profile") MultipartFile multipartFile) throws IOException{
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setProfile_img(fileName);
            
            User savedUser = userService.updateUser(user);
            
            String profileDir = "/uploads/" + savedUser.getId();
            
            FileUploadUtility.saveFile(profileDir, fileName, multipartFile);

        }else{
            if(user.getProfile_img().isEmpty()) user.setProfile_img(null);
            userService.updateUser(user);
        }
//        userService.createUser(user);
       loginUser.setFirstName(user.getFirstname());
       loginUser.setLastName(user.getLastname());
       
       return "redirect:/user_profile";
    }
    
    //change password handler
    @PostMapping("/user_profile/change_password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, 
            @RequestParam("newPassword") String newPassword, 
            @AuthenticationPrincipal UserPrincipal loginUser,
            HttpSession session, RedirectAttributes redAtt){
        
        System.out.println("OLD Password " + oldPassword);
        System.out.println("NEW Password " + newPassword);
        
       String email = loginUser.getUsername();
       User currentUser = userService.findByEmail(email);
       System.out.println("Logged In User " + currentUser); 
       System.out.println(currentUser.getPassword());
       
       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        
       if(bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())){
           //change password
           
           currentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
           this.userRepository.save(currentUser);
           System.out.println("Password change successfully...");
           session.setAttribute("message", "Password Changed Success");
           redAtt.addFlashAttribute("message", "Password changed successfully.");
           
       }else{
           //error
           System.out.println("Wrong old Password ");
           session.setAttribute("message", "Enter correct old password");
           redAtt.addFlashAttribute("message", "Enter correct old password.");
       } 
        
        return "redirect:/user_profile";
    }

}
