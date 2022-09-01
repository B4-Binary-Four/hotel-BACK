package com.codingpals.hotel.model.validator;

import com.codingpals.hotel.model.Booking;
import com.codingpals.hotel.model.Room;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {
  public void accept(Booking booking) {
    if (!this.isValid(booking)) {
      throw new RuntimeException("This Room is already occupied");
    }
  }

  public void accept(Booking booking, int id) {
    if (!this.isValid(booking,id)) {
      throw new RuntimeException("This Room is already occupied");
    }
  }

  public void accept(List<Booking> bookingList) {
    bookingList.forEach(this::accept);
  }

  private boolean isValid(Booking booking) {
    return hasAvailableRoom(booking) && hasValidEndDate(booking);
  }

  private boolean isValid(Booking booking, int id) {
    return hasAvailableRoom(booking) && hasValidEndDate(booking) && booking.getId() == id;
  }

  private boolean hasAvailableRoom(Booking booking) {
    return booking.getRoom().getStatus() != Room.Status.TAKEN;
  }


  private boolean hasValidEndDate(Booking booking) {
    return booking.getBookingDate().isBefore(booking.getBookingDateEnd());
  }
}
