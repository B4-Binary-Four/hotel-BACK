package com.codingpals.hotel.controller.mapper;

import com.codingpals.hotel.model.RoomCategory;
import com.codingpals.hotel.model.rest.CreateRoomCategory;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  public com.codingpals.hotel.model.rest.RoomCategory toRest(RoomCategory roomCategory) {
    return com.codingpals.hotel.model.rest.RoomCategory.builder()
        .id(roomCategory.getId())
        .categoryName(roomCategory.getCategoryName())
        .price(roomCategory.getPrice())
        .build();
  }

  public RoomCategory toDomain(CreateRoomCategory createRoomCategory) {
    return RoomCategory.builder()
        .categoryName(createRoomCategory.getCategoryName())
        .price(createRoomCategory.getPrice())
        .build();
  }

  public RoomCategory toDomain(com.codingpals.hotel.model.rest.RoomCategory roomCategoryRest) {
    return RoomCategory.builder()
        .categoryName(roomCategoryRest.getCategoryName())
        .price(roomCategoryRest.getPrice())
        .id(roomCategoryRest.getId())
        .build();
  }
}
