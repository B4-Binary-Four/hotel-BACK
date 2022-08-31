package com.codingpals.hotel.model.rest;

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
public class Room {
  private int id;
  private String roomName;
  private String description;
  private com.codingpals.hotel.model.Room.Status status;
  private String categoryName;
  private double price;
  private int bookingCount;
}
