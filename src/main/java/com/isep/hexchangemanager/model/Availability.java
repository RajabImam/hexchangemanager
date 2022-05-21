package com.isep.hexchangemanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date availability_start;
    private Date availability_end;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
}
