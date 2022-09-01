package com.codingpals.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Enumerated(EnumType.STRING)
  @Transient
  private Status status;
  @Column(unique = true)
  private String name;
  private String description;
  @ManyToOne
  private RoomCategory category;
  @Transient
  private double price;
  @Transient
  private int bookingCount;

  public double getPrice() {
    return category.getPrice();
  }

  public enum Status {
    AVAILABLE, TAKEN
  }
}
