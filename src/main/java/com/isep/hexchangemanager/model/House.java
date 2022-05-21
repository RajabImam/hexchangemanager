/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author RAJAB IMAM
 */
@Entity
@Data
public class House extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String postalcode;
    private String city;
    private String country;
    private String type;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Images> images;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<HService> services;

    @ToString.Exclude
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HConstraint> constraints;

    @OneToOne(mappedBy = "house", cascade = CascadeType.ALL)
    private Booking booking;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Availability> availability;
}