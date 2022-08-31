package com.codingpals.hotel.model.rest;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateRoomCategory {
    private String categoryName ;
    private double price ;
}
