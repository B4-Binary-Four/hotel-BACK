package com.codingpals.hotel.repository;

import com.codingpals.hotel.model.Booking;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
  @Query("select count(b.room) from Booking b where b.room.id = :roomId")
  int getBookingCount(@Param("roomId") int roomId);
  @Query("select b from Booking b where b.room.category.categoryName = :categoryName")
  List<Booking> findByCategoryName(Pageable pageable, String categoryName);
}
