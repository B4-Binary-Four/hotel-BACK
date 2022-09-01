package com.codingpals.hotel.model.rest;

import java.time.Instant;
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
public class CreateBooking {
  private String clientName;
  private String phoneNumber;
  private Instant bookingDate;
  private Instant bookingDateEnd;
  private String roomName;
}
