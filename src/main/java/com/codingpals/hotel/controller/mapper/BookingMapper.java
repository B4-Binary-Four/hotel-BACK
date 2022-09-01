package com.codingpals.hotel.controller.mapper;

import com.codingpals.hotel.model.Booking;
import com.codingpals.hotel.model.rest.CreateBooking;
import com.codingpals.hotel.service.RoomService;
import java.time.Instant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookingMapper {
  private final RoomService roomService;

  public Booking toDomain(com.codingpals.hotel.model.rest.Booking booking) {
    return Booking.builder()
        .id(booking.getId())
        .bookingDate(booking.getBookingDate())
        .bookingDateEnd(booking.getBookingDateEnd())
        .clientName(booking.getClientName())
        .phoneNumber(booking.getPhoneNumber())
        .room(roomService.getByName(booking.getRoomName()))
        .build();
  }

  public Booking toDomain(CreateBooking createBooking) {
    return Booking.builder()
        .clientName(createBooking.getClientName())
        .room(roomService.getByName(createBooking.getRoomName()))
        .phoneNumber(createBooking.getPhoneNumber())
        .creationDate(Instant.now())
        .bookingDate(createBooking.getBookingDate())
        .bookingDateEnd(createBooking.getBookingDateEnd())
        .build();
  }

  public com.codingpals.hotel.model.rest.Booking toRest(Booking booking) {
    return com.codingpals.hotel.model.rest.Booking.builder()
        .id(booking.getId())
        .bookingDate(booking.getBookingDate())
        .phoneNumber(booking.getPhoneNumber())
        .creationDate(booking.getCreationDate())
        .bookingDateEnd(booking.getBookingDateEnd())
        .clientName(booking.getClientName())
        .roomName(booking.getRoom().getName())
        .roomCategoryName(booking.getRoom().getCategory().getCategoryName())
        .build();
  }
}
