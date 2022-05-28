/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.controller;

import com.isep.hexchangemanager.form.housemanagement.AddImageForm;
import com.isep.hexchangemanager.form.housemanagement.GroupOrder;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.Image;
import com.isep.hexchangemanager.service.IHouseService;
import com.isep.hexchangemanager.service.IImageService;
import com.isep.hexchangemanager.util.FileUploadUtility;
import com.isep.hexchangemanager.util.Message;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author RAJAB IMAM
 */
@RequestMapping("/image")
@Controller
@Slf4j
public class ImagesController {
    @Autowired
    private IImageService imageService;
    
    @Autowired
    private IHouseService houseService;

    @Autowired
    private ModelMapper modelMapper ;
    
     //Open the add image form
    @GetMapping("/add")
    public String addImagesForm(Model model, @ModelAttribute AddImageForm form){   
        //, @RequestParam("houseId") Long houseId
        //model.addAttribute("houseId", houseId);
        //model.addAttribute("houseimages", new Images());
        //return "/image/addimages";
        return "house/list";
    }
    
    //add images to the db
    @PostMapping("/add")
    public String addHouseImages(Model model , Principal principal,
                               @ModelAttribute @Validated(GroupOrder.class ) AddImageForm form ,
                               BindingResult bindingResult, 
            //@RequestParam("houseId" ) Long houseId, 
            @RequestParam("mainImage") MultipartFile mainMultipartFile, HttpSession session) throws IOException{
        
         // Input check result
        if (bindingResult.hasErrors()) {
        // NG: Return to the user signup screen
            return addImagesForm(model , form);
        }
        log.info(form .toString());

        //get house id from the form
        Long houseId = form.getHouseId();

        //map form details to the house model
        Image images = modelMapper.map(form, Image.class);
        
        String mainImageName = StringUtils.cleanPath(mainMultipartFile.getOriginalFilename());
        images.setMain_image(mainImageName);
        
//        int count = 0;
//        for (MultipartFile extramultipartFile1 : extramultipartFile) {
//            String extraImageName = StringUtils.cleanPath(extramultipartFile1.getOriginalFilename());
//           if(count == 0) images.setExtra_image_1(extraImageName);
//           if(count == 1) images.setExtra_image_2(extraImageName);
//           if(count == 2) images.setExtra_image_3(extraImageName);
//           
//           count++;
//        }

        try{
        //get house details using the houseId
        House house = houseService.findById(houseId).get();

        //set the house field of the house model
        images.setHouse(house);

        //add constraint to database
        Image savedImage = imageService.addImage(images);
            
             //Images savedImage = imageService.addImage(images);
         String houseImagesDir = "./house-images/" + savedImage.getId();
         
         FileUploadUtility.saveFile(houseImagesDir, mainImageName, mainMultipartFile);
             
            model.addAttribute("status", "Image added successfully");
            //success message
            session.setAttribute("message", new Message("Image Added Successfully - Add more ...", "success"));
        }
        catch (Exception e){
            model.addAttribute("status", "Images added not successful");
            log.error(e.getMessage());
            //error message
            session.setAttribute("message", new Message("Something went wrong - Try again ...", "danger"));
        }
        
          // Redirect to Dashboard    
        return "redirect:/house/list";
    }
    
    @GetMapping("/list/{id}")
    public String houseList(Model model,  @PathVariable("userId" ) Long id){
        House house;
        List<Image> image = new ArrayList<>();
        Optional<House> result = houseService.findById(id);

        if(result.isPresent()){
            house = result.get();
            image = imageService.findHouseImage(house);
        }

        model .addAttribute("image" , image );

        return "house/list";
    }
}
