package com.codingpals.hotel.model.validator;

import com.codingpals.hotel.model.Booking;
import com.codingpals.hotel.model.Room;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {
  public void accept(Booking booking) {
    if (!this.hasAvailableRoom(booking)) {
      throw new RuntimeException("This Room is already occupied");
    }
  }

  public void accept(List<Booking> bookingList) {
    bookingList.forEach(this::accept);
  }

  private boolean hasAvailableRoom(Booking booking) {
    return booking.getRoom().getStatus() == Room.Status.AVAILABLE;
  }
}
