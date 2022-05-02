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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author RAJAB IMAM
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
     @GetMapping("/signup")
    public String createAccountForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }
    
   /* @PostMapping("/signup")
    public String createUserAccount(@Validated User user, BindingResult bindingResult, Model model){
       
        userService.createUser(user);
        return "success";
    }*/
    
    @PostMapping("/signup")
    public RedirectView createUserAccount(User user, RedirectAttributes redAtt){
       
        userService.createUser(user);
        RedirectView redView = new RedirectView("/login", true);
        redAtt.addFlashAttribute("message", "Account created successfully. You can now login");
        
        return redView;
    }
    
    @GetMapping("/users")
    public String usersList(Model model){
        model.addAttribute("users", userService.getUsers());
        return "dashboard/users";
    }
    
    
    
    @RequestMapping("users/findById")
    @ResponseBody
    public Optional<User> findById(Long id){
        return userService.findById(id);
    }
    
    @RequestMapping(value = "/users/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(User user, RedirectAttributes redirectAttributes, 
            @AuthenticationPrincipal UserPrincipal loginUser, 
            @RequestParam("image") MultipartFile multipartFile) throws IOException{
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setProfile_img(fileName);
            
            userService.createUser(user);
            String profileFolder = "images/profiles/" + user.getEmail();
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
        return "redirect:/users";
    }
    
    @RequestMapping(value = "/users/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Long id){
        userService.delete(id);
        return "redirect:/users";
    }
}
