/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author RAJAB IMAM
 */
public class MvcConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path profileUploadDir = Paths.get("/uploads");
        String profileUploadPath = profileUploadDir.toFile().getAbsolutePath();
        
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:/" + profileUploadPath + "/");
      
    }
    
    
}
