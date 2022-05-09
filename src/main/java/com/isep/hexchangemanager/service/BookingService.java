/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service;

import com.isep.hexchangemanager.model.Booking;
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
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    // Return all bookings
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    // Add new booking
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    // find booking by id
    public Optional<Booking> findById(int id) {
        return bookingRepository.findById(id);
    }

    // delete booking by id
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

}
