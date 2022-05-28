/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.Booking;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.repository.BookingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public interface IBookingService {
    // Return all bookings
    List<Booking> getBookings();

    // Add new booking
    void save(Booking booking);

    // find booking by id
    Optional<Booking> findById(int id);

    // delete booking by id
    void delete(Integer id);

    // get all bookings associated with a house
    List<Booking> findHouseBooking(House house);

    // get all bookings associated with a user
    List<Booking> findUserBooking(User user);
}
