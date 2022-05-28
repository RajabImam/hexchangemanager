/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.repository;

import com.isep.hexchangemanager.model.House;
import com.isep.hexchangemanager.model.User;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RAJAB IMAM
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Long>{
    //find house by availability and country

    @Query(value = "SELECT DISTINCT H.id, H.created_by, H.created_date, H.last_modified_by, " +
            "H.last_modified_date, H.address, H.city, H.country, H.description, H.name, " +
            "H.postalcode, H.type, H.user_id from house H " +
            "RIGHT JOIN availability A on " +
            "H.id = A.house_id WHERE " +
            "H.country = :country AND " +
            "A.start_date = :startDate AND " +
            "A.end_date = :endDate",
            nativeQuery = true)
    List<House> findAvailableHouseByDurationAndCountry(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("country") String country);

    @Query(value = "SELECT DISTINCT H.id, H.created_by, H.created_date, H.last_modified_by, " +
            "H.last_modified_date, H.address, H.city, H.country, H.description, H.name, " +
            "H.postalcode, H.type, H.user_id from house H " +
            "RIGHT JOIN availability A on " +
            "H.id = A.house_id WHERE " +
            "H.country = :country",
            nativeQuery = true)
    List<House> findAvailableHouseByCountry(@Param("country") String country);

    @Query(value = "SELECT DISTINCT H.id, H.created_by, H.created_date, H.last_modified_by, " +
            "H.last_modified_date, H.address, H.city, H.country, H.description, H.name, " +
            "H.postalcode, H.type, H.user_id from house H " +
            "RIGHT JOIN availability A on " +
            "H.id = A.house_id WHERE " +
            "A.start_date = :startDate",
            nativeQuery = true)
    List<House> findAvailableHouseByStartDate(@Param("startDate") String startDate);


    List<House> findByUser(User user);
}
