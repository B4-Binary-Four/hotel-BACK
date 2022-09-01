package com.codingpals.hotel.repository;

import com.codingpals.hotel.model.Room;
import com.codingpals.hotel.model.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    Optional<Room> findByName(String name) ;

    @Query("select room from Room room where room.category.categoryName= :roomCategory ")
    List<Room> findAllByCategory(@Param("roomCategory") String roomCategory) ;

}
