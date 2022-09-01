package com.codingpals.hotel.controller;

import com.codingpals.hotel.controller.mapper.RoomMapper;
import com.codingpals.hotel.model.BoundedPageSize;
import com.codingpals.hotel.model.PageFromOne;
import com.codingpals.hotel.model.rest.CreateRoom;
import com.codingpals.hotel.model.rest.Room;
import com.codingpals.hotel.service.RoomService;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RoomController {
  private final RoomMapper roomMapper;
  private final RoomService roomService;

  @GetMapping("/rooms")
  public List<Room> getRooms(@RequestParam PageFromOne page,
                             @RequestParam BoundedPageSize pageSize,
                              @RequestParam(required = false , defaultValue = "") String category ,
                             @RequestParam Instant instant
                              ) {
    return roomService.getAll(page, pageSize , category).stream().map((room) -> roomMapper.toRest(room , instant)).toList();
  }

  @PostMapping("/rooms")
  public Room createRoom(@RequestBody CreateRoom createRoom) {
    return roomMapper.toRest((roomService.save(roomMapper.toDomain(createRoom))), Instant.now());
  }

  @GetMapping("/rooms/{id}")
  public Room getRoomById(@PathVariable int id , @RequestParam Instant instant) {
    return roomMapper.toRest(roomService.getById(id) , instant);
  }

  @PutMapping("/rooms/{id}")
  public Room updateRoom(@PathVariable int id, @RequestBody Room room) {
    return roomMapper.toRest(roomService.updateRoom(id, roomMapper.toDomain(room)) , Instant.now());
  }
}
