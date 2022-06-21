/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author RAJAB IMAM
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{

   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*Path profileUploadDir = Paths.get("./uploads");
        Path houseImagesDir = Paths.get("./house-images/");
        String profileUploadPath = profileUploadDir.toFile().getAbsolutePath();
        String houseUploadPath = houseImagesDir.toFile().getAbsolutePath();*/
        
        /*registry.addResourceHandler("/uploads/**").addResourceLocations("file:/" + profileUploadPath + "/");
        //registry.addResourceHandler("/house-images/**").addResourceLocations("file:/" + houseUploadPath + "/");
        registry.addResourceHandler("/house-images/**").addResourceLocations("file:resources/","file:uploads/","file:/C:\\Users\\RAJAB IMAM\\Documents\\NetBeansProjects\\hexchangemanager\\house-images\\1");
        
  
      
    }*/
    
           @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("house-images", registry);
        exposeDirectory("uploads", registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
}
