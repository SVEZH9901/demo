package com.example.demo.service;

import com.example.demo.dto.RoomDto;
import com.example.demo.dto.RoomsResponse;

public interface RoomService {
    RoomDto createRoom(RoomDto room);
    RoomsResponse getAllRooms(int pageNo, int pageSize);
    RoomDto getRoomById(int id);
    RoomDto updateRoom(RoomDto roomDto, int id);
    void deleteRoomById(int id);
}
