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
public class Booking {
  private int id;
  private String clientName;
  private String phoneNumber;
  private Instant bookingDate;
  private String roomCategoryName;
  private int roomId;
  private Instant creationDate;
}