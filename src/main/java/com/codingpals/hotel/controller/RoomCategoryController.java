package com.codingpals.hotel.controller;


import com.codingpals.hotel.controller.mapper.CategoryMapper;
import com.codingpals.hotel.model.rest.CreateRoomCategory;
import com.codingpals.hotel.model.rest.RoomCategory;
import com.codingpals.hotel.service.RoomCategoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class RoomCategoryController {

  private RoomCategoryService roomCategoryService;
  private CategoryMapper categoryMapper;

  @GetMapping("/roomCategories")
  public List<RoomCategory> getRoomCategoryList() {
    return roomCategoryService.getRoomCategories().stream().map(categoryMapper::toRest).toList();
  }

  @GetMapping("/roomCategories/{id}")
  public RoomCategory getRoomCategoryById(@PathVariable int id) {
    return categoryMapper.toRest(roomCategoryService.getRoomCategoryById(id));
  }

  @PostMapping("/roomCategories")
  public RoomCategory saveRoomCategory(@RequestBody CreateRoomCategory toCreate) {
    return categoryMapper.toRest(
        roomCategoryService.saveRoomCategory(categoryMapper.toDomain(toCreate)));
  }

  @PutMapping("/roomCategories/{id}")
  public RoomCategory updateRoomCategory(@PathVariable int id,
                                         @RequestBody RoomCategory roomCategory) {
    return categoryMapper.toRest(
        roomCategoryService.updateCategory(id, categoryMapper.toDomain(roomCategory)));
  }


}
