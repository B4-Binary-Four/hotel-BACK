package com.codingpals.hotel.model;

import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;

import com.codingpals.hotel.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String clientName;
  private String phoneNumber;

  @FutureOrPresent
  private Instant bookingDate;

  @FutureOrPresent
  private Instant bookingDateEnd;
  private Instant creationDate;

  @ManyToOne
  private Room room;
}

//select name from booking inner join room where booking.id = room.id where booking.bookingDate = bookingDate
//and booking.bookingDateEnd = bookingDateEnd and room.status = availaible