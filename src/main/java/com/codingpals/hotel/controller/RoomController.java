package com.codingpals.hotel.controller;

import com.codingpals.hotel.controller.mapper.RoomMapper;
import com.codingpals.hotel.model.BoundedPageSize;
import com.codingpals.hotel.model.PageFromOne;
import com.codingpals.hotel.model.rest.CreateRoom;
import com.codingpals.hotel.model.rest.Room;
import com.codingpals.hotel.service.RoomService;
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
                             @RequestParam BoundedPageSize pageSize) {
    return roomService.getAll(page, pageSize).stream().map(roomMapper::toRest).toList();
  }

  @PostMapping("/rooms")
  public Room createRoom(@RequestBody CreateRoom createRoom) {
    return roomMapper.toRest((roomService.save(roomMapper.toDomain(createRoom))));
  }

  @GetMapping("/rooms/{id}")
  public Room getRoomById(@PathVariable int id) {
    return roomMapper.toRest(roomService.getById(id));
  }

  @PutMapping("/rooms/{id}")
  public Room updateRoom(@PathVariable int id, @RequestBody Room room) {
    return roomMapper.toRest(roomService.updateRoom(id, roomMapper.toDomain(room)));
  }
}
