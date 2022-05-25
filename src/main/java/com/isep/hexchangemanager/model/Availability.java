package com.isep.hexchangemanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private Integer status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    private House house;
}
