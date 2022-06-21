/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author RAJAB IMAM
 */
@Data
@Entity
public class Booking extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;
    private Integer owner_rating;
    private Integer house_rating;

    @Column(name="booking_date")
    private Date bookingDate;

    @Column(name="start_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;

    @Column(name="end_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    private House house;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_id", referencedColumnName = "id")
    private Availability availability;
}
