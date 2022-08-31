package com.codingpals.hotel.model.validator;

import com.codingpals.hotel.model.RoomCategory;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {
  public void accept(RoomCategory roomCategory) {
    if (!this.hasValidPrice(roomCategory)) {
      throw new RuntimeException("Room category price can't be negative");
    }
  }

  public void accept(List<RoomCategory> roomCategoryList) {
    roomCategoryList.forEach(this::accept);
  }

  private boolean hasValidPrice(RoomCategory roomCategory) {
    if (roomCategory.getPrice() < 0) {
      return false;
    }
    return true;
  }

}
