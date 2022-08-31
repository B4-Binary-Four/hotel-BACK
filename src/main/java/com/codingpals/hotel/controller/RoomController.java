package com.codingpals.hotel.controller;

import com.codingpals.hotel.controller.mapper.RoomMapper;
import com.codingpals.hotel.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RoomController {
  private final RoomMapper roomMapper;
  private final RoomService roomService;
}
