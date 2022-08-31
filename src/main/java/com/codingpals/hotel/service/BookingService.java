package com.codingpals.hotel.service;

import com.codingpals.hotel.model.Booking;
import com.codingpals.hotel.model.BoundedPageSize;
import com.codingpals.hotel.model.PageFromOne;
import com.codingpals.hotel.model.Room;
import com.codingpals.hotel.model.validator.BookingValidator;
import com.codingpals.hotel.repository.BookingRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {
  private final BookingRepository bookingRepository;
  private final BookingValidator bookingValidator;

  public List<Booking> getBookings(PageFromOne page, BoundedPageSize pageSize,
                                   String roomCategoryName) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
    if (roomCategoryName == null || roomCategoryName.isBlank()) {
      return bookingRepository.findAll(pageable).getContent();
    }
    return bookingRepository.findByCategoryName(pageable, roomCategoryName);
  }

  @Transactional
  public Booking saveBooking(Booking booking) {
    bookingValidator.accept(booking);
    booking.getRoom().setStatus(Room.Status.TAKEN);
    return bookingRepository.save(booking);
  }

  public Booking getBookingById(int id) {
    Optional<Booking> book = bookingRepository.findById(id);
    if (book.isPresent()) {
      return book.get();
    }
    throw new RuntimeException("Booking not found");
  }

  public int getBookingCount(int roomId) {
    return bookingRepository.getBookingCount(roomId);
  }

  @Transactional
  public Booking updateBooking(int id, Booking booking) {
    bookingValidator.accept(booking);
    Booking toUpdate = this.getBookingById(id);
    if (toUpdate.getRoom().getId() != booking.getRoom().getId()) {
      toUpdate.getRoom().setStatus(Room.Status.AVAILABLE);
      booking.getRoom().setStatus(Room.Status.TAKEN);
    }
    toUpdate.setBookingDate(booking.getBookingDate());
    toUpdate.setPhoneNumber(booking.getPhoneNumber());
    toUpdate.setRoom(booking.getRoom());
    toUpdate.setClientName(booking.getClientName());
    return bookingRepository.save(toUpdate);
  }
}
