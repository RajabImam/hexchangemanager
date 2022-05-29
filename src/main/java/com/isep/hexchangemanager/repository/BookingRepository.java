/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.repository;

import com.isep.hexchangemanager.model.*;
import com.isep.hexchangemanager.model.viewmodel.BookingHouseUserAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "update booking set status = 1 where id = :bookingId",
            nativeQuery = true)
    void approveBooking(@Param("bookingId")Long bookingId);

    @Modifying
    @Query(value = "update booking set status = 2 where id != :bookingId and availability_id = :availabilityId and status = 0",
            nativeQuery = true)
    void rejectOtherBookings(@Param("bookingId")Long bookingId, @Param("availabilityId")Long availabilityId);

    @Modifying
    @Query(value = "delete from availability where id = :availabilityId",
            nativeQuery = true)
    void deleteAvailability(@Param("availabilityId")Long availabilityId);

    @Modifying
    @Query(value = "update booking set status = 2 where id = :bookingId",
            nativeQuery = true)
    void rejectBookingwithBookingId(@Param("bookingId")Long bookingId);
}
