package com.codingpals.hotel.service;

import com.codingpals.hotel.model.*;
import com.codingpals.hotel.model.validator.BookingValidator;
import com.codingpals.hotel.repository.BookingRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.codingpals.hotel.security.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {
  private final BookingRepository bookingRepository;
  private final BookingValidator bookingValidator;

  private final UserService userService ;

  public List<Booking> getBookings(PageFromOne page, BoundedPageSize pageSize,
                                   String roomCategoryName) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
    if (roomCategoryName == null || roomCategoryName.isBlank()) {
      return bookingRepository.findAll(pageable).getContent();
    }
    return bookingRepository.findByCategoryName(pageable, roomCategoryName);
  }

  public Room.Status getStatus(int roomId , Instant instant){
      Room room = bookingRepository.getRoomStatus(roomId , instant) ;
      if(room == null){
        return Room.Status.AVAILABLE ;
      }
      return Room.Status.TAKEN ;
  }

  @Transactional
  public Booking saveBooking(Booking booking) {
    if(getStatus(booking.getRoom().getId() , booking.getBookingDate()) != Room.Status.TAKEN) {
      bookingValidator.accept(booking);
      booking.getRoom().setStatus(Room.Status.TAKEN);
      User user = new User();
      user.setUsername(booking.getPhoneNumber());
      user.setPassword(booking.getPhoneNumber());
      user.setRole(Role.CLIENT);
      user.setEnabled(true);
    userService.saveUser(user);
    return bookingRepository.save(booking);
    }
    throw new RuntimeException("ROOM ALREADY OCCUPIED") ;
  }

  public Booking getBookingById(int id) {
    Optional<Booking> book = bookingRepository.findById(id);
    if (book.isPresent()) {
      return book.get();
    }
    throw new RuntimeException("Booking not found");
  }

  public int getRoomBookingCount(Room room) {
    return bookingRepository.getRoomBookingCount(room.getId());
  }

  public int getCategoryBookingCount(RoomCategory category) {
    return bookingRepository.getCategoryBookingCount(category.getId());
  }

  @Transactional
  public Booking updateBooking(int id, Booking booking) {
    booking.setId(id);
    bookingValidator.accept(booking, id);
    Booking toUpdate = this.getBookingById(id);
    if (toUpdate.getRoom().getId() != booking.getRoom().getId()) {
      toUpdate.getRoom().setStatus(Room.Status.AVAILABLE);
      booking.getRoom().setStatus(Room.Status.TAKEN);
    }
    toUpdate.setBookingDate(booking.getBookingDate());
    toUpdate.setBookingDateEnd(booking.getBookingDateEnd());
    toUpdate.setPhoneNumber(booking.getPhoneNumber());
    toUpdate.setRoom(booking.getRoom());
    toUpdate.setClientName(booking.getClientName());
    return bookingRepository.save(toUpdate);
  }
}
