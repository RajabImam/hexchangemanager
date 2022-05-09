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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author RAJAB IMAM
 */
@Entity
public class House extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String type;
    private String description;
    private Integer status;
    private Date availability_start;
    private Date availability_end;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Images> images;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Services> services;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Constraints> constraints;

    @OneToOne(mappedBy = "house", cascade = CascadeType.ALL)
    private Booking booking;

    public House() {
    }

    public House(String name, String location, String type, String description, Integer status, Date availability_start,
            Date availability_end, User user) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.status = status;
        this.availability_start = availability_start;
        this.availability_end = availability_end;
        this.user = user;
    }

    public House(String name, String location, String type, String description, Integer status, Date availability_start,
            Date availability_end) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.status = status;
        this.availability_start = availability_start;
        this.availability_end = availability_end;
    }

    public House(String name, String location, String type, String description, Integer status, Date availability_start,
            Date availability_end, User user, List<Images> images, List<Services> services,
            List<Constraints> constraints) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.status = status;
        this.availability_start = availability_start;
        this.availability_end = availability_end;
        this.user = user;
        this.images = images;
        this.services = services;
        this.constraints = constraints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAvailability_start() {
        return availability_start;
    }

    public void setAvailability_start(Date availability_start) {
        this.availability_start = availability_start;
    }

    public Date getAvailability_end() {
        return availability_end;
    }

    public void setAvailability_end(Date availability_end) {
        this.availability_end = availability_end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public List<Constraints> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraints> constraints) {
        this.constraints = constraints;
    }

}
