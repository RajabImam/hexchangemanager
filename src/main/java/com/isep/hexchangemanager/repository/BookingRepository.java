/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.repository;

import com.isep.hexchangemanager.model.*;
import com.isep.hexchangemanager.model.viewmodel.BookingHouseUserAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author RAJAB IMAM
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);
    List<Booking> findByHouse(House house);

    @Query(value = "update booking set status = 1 where booking = :bookingId;" +
            "update booking set status = 2 where booking != :bookingId and availability_id = :availabilityId and status = 0;" +
            "delete from availability where availability_id = :availabilityId",
            nativeQuery = true)
    void approveBookingwithBookingIdAvailabilityId(@Param("bookingId")Long bookingId, @Param("availabilityId")Long availabilityId);

    @Query(value = "update booking set status = 2 where id = :bookingId",
            nativeQuery = true)
    void rejectBookingwithBookingId(@Param("bookingId")Long bookingId);
}
