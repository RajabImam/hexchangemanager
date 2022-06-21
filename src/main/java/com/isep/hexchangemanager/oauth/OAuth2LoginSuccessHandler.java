/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.oauth;

import com.isep.hexchangemanager.model.Provider;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author RAJAB IMAM
 */
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = customOAuth2User.getEmail();
        User user = userService.findByEmail(email);
        String name = customOAuth2User.getName();
        
        if (user == null) {
            //register as a new user
            userService.createOAuthUser(email, name, Provider.GOOGLE);
            response.sendRedirect("/dashboard");
        } else {
            //update existing user
            userService.updateOAuthUser(user, name, Provider.GOOGLE);
            response.sendRedirect("/dashboard");
        }
        
        System.out.println("OAuth User Email : " + email);
        super.onAuthenticationSuccess(request, response, authentication); 
    }
    
}
