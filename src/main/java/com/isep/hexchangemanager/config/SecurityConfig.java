/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.config;

import com.isep.hexchangemanager.oauth.CustomOAuth2UserService;
import com.isep.hexchangemanager.oauth.OAuth2LoginSuccessHandler;
import com.isep.hexchangemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author RAJAB IMAM
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
       
    @Autowired
    private UserService userService;
    
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select email,password,enabled from user where email=?")
//                .authoritiesByUsernameQuery("select user_id,role_id from users_roles where user_id=?")
//                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
        auth.authenticationProvider(authenticationProvider());
          
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
   
    @Bean
    public PasswordEncoder passwordEncoder(){
        
        return new BCryptPasswordEncoder();
    
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests().antMatchers("/","/about","/discovery","/faqs","/contact","/signup","/login","/oauth2/**","/css/**","/images/**","/js/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    //.usernameParameter("email")
                    .permitAll()
                    .defaultSuccessUrl("/dashboard")
                .and()
                .oauth2Login()
                    .loginPage("/login")
                    .permitAll()
                    //.defaultSuccessUrl("/dashboard", true)
                    .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2LoginSuccessHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
    
    
}
