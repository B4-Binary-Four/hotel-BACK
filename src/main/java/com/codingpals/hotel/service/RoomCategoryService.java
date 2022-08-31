package com.codingpals.hotel.service;

import com.codingpals.hotel.model.RoomCategory;
import com.codingpals.hotel.repository.RoomCategoryRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoomCategoryService {
  private RoomCategoryRepository roomCategoryRepository;

  public List<RoomCategory> getRoomCategories() {
    return roomCategoryRepository.findAll();
  }

  public RoomCategory getRoomCategoryById(int id) {
    Optional<RoomCategory> optCategory = roomCategoryRepository.findById(id);
    if (optCategory.isPresent()) {
      return optCategory.get();
    }
    throw new RuntimeException("Room category not found");
  }

  public RoomCategory saveRoomCategory(RoomCategory roomCategory) {
    return roomCategoryRepository.save(roomCategory);
  }

  @Transactional
  public RoomCategory updateCategory(int id, RoomCategory roomCategory) {
    RoomCategory updatedRoomCategory = this.getRoomCategoryById(id);

    updatedRoomCategory.setCategoryName(roomCategory.getCategoryName());
    updatedRoomCategory.setPrice(roomCategory.getPrice());

    return roomCategoryRepository.save(updatedRoomCategory);
  }


}
