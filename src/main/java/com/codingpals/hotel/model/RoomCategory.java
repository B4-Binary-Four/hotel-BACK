package com.codingpals.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class RoomCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String categoryName;

  private double price;

  @Transient
  private int bookingCount;
}
