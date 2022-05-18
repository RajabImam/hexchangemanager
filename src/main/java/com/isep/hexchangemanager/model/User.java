/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


/**
 *
 * @author RAJAB IMAM
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String gender;
    private Date dob;
    private String phone;
    private String address;
    private String email;
    private String password;
    private String profile_img;
    
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<House> houses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String firstname, String lastname, String gender, Date dob, String phone, String address, String email,
            String password, String profile_img) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.profile_img = profile_img;
    }

    public User(String firstname, String lastname, String gender, Date dob, String phone, String address, String email,
            String password, String profile_img, List<Role> roles, List<House> houses, List<Booking> bookings) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.profile_img = profile_img;
        this.roles = roles;
        this.houses = houses;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }
    
    @Transient
    public String getProfileImagePath(){
        if(profile_img == null || id == null ) return null;
            
        return "/uploads/" + id + "/" + profile_img;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
                + ", dob=" + dob + ", phone=" + phone + ", address=" + address + ", email=" + email + ", password="
                + password + ", profile_img=" + profile_img + ", roles=" + roles + ", houses=" + houses + ", bookings="
                + bookings + '}';
    }

}
