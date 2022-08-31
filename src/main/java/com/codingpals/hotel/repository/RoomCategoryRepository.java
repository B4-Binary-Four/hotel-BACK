package com.codingpals.hotel.repository;

import com.codingpals.hotel.model.RoomCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Integer> {
  Optional<RoomCategory> getRoomCategoryByCategoryName(String categoryName);
}
