package com.codingpals.hotel.controller;


import com.codingpals.hotel.controller.mapper.BookingMapper;
import com.codingpals.hotel.model.BoundedPageSize;
import com.codingpals.hotel.model.PageFromOne;
import com.codingpals.hotel.model.rest.Booking;
import com.codingpals.hotel.model.rest.CreateBooking;
import com.codingpals.hotel.service.BookingService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class BookingController {
  private final BookingService bookingService;
  private final BookingMapper bookingMapper;

  @GetMapping("/bookings")
  public List<Booking> getBookings(@RequestParam PageFromOne page,
                                   @RequestParam BoundedPageSize pageSize,
                                   @RequestParam(required = false) String roomCategoryName) {
    return bookingService.getBookings(page, pageSize, roomCategoryName).stream()
        .map(bookingMapper::toRest).toList();
  }

  @GetMapping("/bookings/{id}")
  public Booking getBooking(@PathVariable int id) {
    return bookingMapper.toRest(bookingService.getBookingById(id));
  }

  @PostMapping("/bookings")
  public Booking book(@RequestBody CreateBooking createBooking) {
    return bookingMapper.toRest(bookingService.saveBooking(bookingMapper.toDomain(createBooking)));
  }

  @PutMapping("/bookings/{id}")
  public Booking updateBook(@PathVariable int id, @RequestBody Booking booking) {
    return bookingMapper.toRest(bookingService.updateBooking(id, bookingMapper.toDomain(booking)));
  }
}
