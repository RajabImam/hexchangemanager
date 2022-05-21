/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author RAJAB IMAM
 */
@Entity
public class Booking extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private Date start_date;
    //private Date expiry_date;
    //private Time estimated_check_in;
    private Integer status;
    private Integer owner_rating;
    private Integer house_rating;

    @Column
    private LocalDate start_date;
    @Column
    private LocalDate expiry_date;


    @OneToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Booking() {
    }

    public Booking(LocalDate start_date, LocalDate expiry_date, Integer status, Integer owner_rating,
            Integer house_rating, House house, User user) {
        this.start_date = start_date;
        this.expiry_date = expiry_date;
        this.status = status;
        this.owner_rating = owner_rating;
        this.house_rating = house_rating;
        this.house = house;
        this.user = user;
    }

    public Booking(LocalDate start_date, LocalDate expiry_date, Integer status, Integer owner_rating,
            Integer house_rating) {
        this.start_date = start_date;
        this.expiry_date = expiry_date;
        this.status = status;
        this.owner_rating = owner_rating;
        this.house_rating = house_rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOwner_rating() {
        return owner_rating;
    }

    public void setOwner_rating(Integer owner_rating) {
        this.owner_rating = owner_rating;
    }

    public Integer getHouse_rating() {
        return house_rating;
    }

    public void setHouse_rating(Integer house_rating) {
        this.house_rating = house_rating;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
