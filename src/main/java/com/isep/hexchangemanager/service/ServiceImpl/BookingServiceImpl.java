/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.service.ServiceImpl;

import com.isep.hexchangemanager.model.Availability;
import com.isep.hexchangemanager.model.Booking;
import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import com.isep.hexchangemanager.model.viewmodel.BookingHouseUserAvailability;
import com.isep.hexchangemanager.repository.AvailabilityRepository;
import com.isep.hexchangemanager.repository.BookingRepository;
import com.isep.hexchangemanager.repository.CustomBookingRepository;
import com.isep.hexchangemanager.service.IAvailabilityService;
import com.isep.hexchangemanager.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author RAJAB IMAM
 */
@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomBookingRepository customBookingRepository;

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> findById(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findHouseBooking(House house) {
        return bookingRepository.findByHouse(house);
    }

    @Override
    public List<Booking> findUserBooking(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<BookingHouseUserAvailability> findBookingRequest(Long userId) {
        return customBookingRepository.findBookingRequest(userId);
    }

    @Override
    public void approveBooking(Long bookingId, Long availabilityId) {
        bookingRepository.approveBookingwithBookingIdAvailabilityId(bookingId,availabilityId);
    }

    @Override
    public void rejectBooking(Long bookingId) {
        bookingRepository.rejectBookingwithBookingId(bookingId);
    }
}
