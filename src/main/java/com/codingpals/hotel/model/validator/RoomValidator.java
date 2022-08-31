package com.codingpals.hotel.model.validator;

import com.codingpals.hotel.model.Room;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoomValidator {
  public void accept(Room room) {
    if (!this.hasValidPrice(room)) {
      throw new RuntimeException("Price mustn't be negative");
    }
  }

  public void accept(List<Room> rooms) {
    rooms.forEach(this::accept);
  }

  private boolean hasValidPrice(Room room) {
    return room.getPrice() > 0;
  }
}
