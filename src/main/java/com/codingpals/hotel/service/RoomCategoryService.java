package com.codingpals.hotel.service;

import com.codingpals.hotel.model.RoomCategory;
import com.codingpals.hotel.model.validator.CategoryValidator;
import com.codingpals.hotel.repository.RoomCategoryRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomCategoryService {
  private final RoomCategoryRepository roomCategoryRepository;
  private final CategoryValidator categoryValidator;

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

  @Transactional
  public RoomCategory saveRoomCategory(RoomCategory roomCategory) {
    categoryValidator.accept(roomCategory);
    return roomCategoryRepository.save(roomCategory);
  }

  @Transactional
  public RoomCategory updateCategory(int id, RoomCategory roomCategory) {
    categoryValidator.accept(roomCategory);
    RoomCategory updatedRoomCategory = this.getRoomCategoryById(id);

    updatedRoomCategory.setCategoryName(roomCategory.getCategoryName());
    updatedRoomCategory.setPrice(roomCategory.getPrice());

    return roomCategoryRepository.save(updatedRoomCategory);
  }

  public RoomCategory getRoomCategoryByName(String categoryName) {
    Optional<RoomCategory> optRoomCategory =
        roomCategoryRepository.getRoomCategoryByCategoryName(categoryName);
    if (optRoomCategory.isPresent()) {
      return optRoomCategory.get();
    }
    throw new RuntimeException("Room category not found");
  }
}
