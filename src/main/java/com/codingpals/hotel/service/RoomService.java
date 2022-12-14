package com.codingpals.hotel.service;

import com.codingpals.hotel.model.BoundedPageSize;
import com.codingpals.hotel.model.PageFromOne;
import com.codingpals.hotel.model.Room;
import com.codingpals.hotel.model.validator.RoomValidator;
import com.codingpals.hotel.repository.RoomRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {
  private final RoomRepository roomRepository;
  private final RoomValidator roomValidator;

  public List<Room> getAll(PageFromOne page, BoundedPageSize pageSize,String category) {
    Pageable pageable =
        PageRequest.of(page.getValue() - 1, pageSize.getValue(),
            Sort.by(Sort.Direction.ASC, "category"));
    if(category.isBlank())
      return roomRepository.findAll(pageable).getContent();
    else return roomRepository.findAllByCategory(category) ;
  }


  @Transactional
  public Room save(Room room) {
    roomValidator.accept(room);
    return roomRepository.save(room);
  }

  public Room getById(int id) {
    Optional<Room> optRoom = roomRepository.findById(id);
    if (optRoom.isPresent()) {
      return optRoom.get();
    }
    throw new RuntimeException("Room not found");
  }
  public Room getByName(String name){
    Optional<Room> optRoom = roomRepository.findByName(name);
    if (optRoom.isPresent()) {
      return optRoom.get();
    }
    throw new RuntimeException("Room not found");
  }
  @Transactional
  public Room updateRoom(int id, Room room) {
    roomValidator.accept(room);
    Room updatedRoom = this.getById(id);
    updatedRoom.setDescription(room.getDescription());
    updatedRoom.setName(room.getName());
    updatedRoom.setStatus(room.getStatus());
    updatedRoom.setCategory(room.getCategory());
    return updatedRoom;
  }
}
