package com.isep.hexchangemanager.model.viewmodel;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class BookingHouseUserAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long bookerId;
    private String bookerEmail;
    private Date bookingDate;
    private Date bookingStart;
    private Date bookingEnd;
    private String bookedHouse;
    private int bookingStatus;
    private Long availabilityId;
    private String houseAddress;
    private String houseCity;
    private String houseCountry;
    private String houseDescription;
    private String houseName;
    private String housePostalCode;
    private String houseType;
    private Long houseOwnerId;
    private String houseOwnerEmail;
}
