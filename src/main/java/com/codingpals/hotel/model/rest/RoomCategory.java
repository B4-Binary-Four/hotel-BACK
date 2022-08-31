package com.codingpals.hotel.model.rest;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RoomCategory {

    private int id ;

    private String categoryName ;

    private double price ;
}
