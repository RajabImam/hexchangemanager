/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.repository;

import com.isep.hexchangemanager.model.Booking;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
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
public interface CustomBookingRepository extends JpaRepository<BookingHouseUserAvailability, Integer> {
    @Query(value = "select b.id as booking_id, b.user_id as booker_id, b.created_by as booker_email, b.created_date as booking_date, " +
            "b.start_date as booking_start, b.end_date as booking_end, b.house_id as booked_house, b.status as booking_status, " +
            "b.availability_id as availability_id, h.address as house_address, h.city as house_city, h.country as house_country, " +
            "h.description as house_description, h.name as house_name, h.postalcode as house_postal_code, h.type as house_type, " +
            "h.user_id as house_owner_id, h.created_by as house_owner_email from booking b " +
            "inner join house h on h.id = b.house_id " +
            "where h.user_id = :userId",
            nativeQuery = true)
    List<BookingHouseUserAvailability> findBookingRequest(@Param("userId") Long userId);
}
