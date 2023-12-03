package com.example.demo.mapper;

import com.example.demo.dto.RoomDto;
import com.example.demo.model.Room;

public class RoomMapper {
    public static RoomDto mapToDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setNumber(room.getNumber());
        roomDto.setType(room.getType());
        return roomDto;
    }

    public static Room mapToEntity(RoomDto roomDto){
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setNumber(roomDto.getNumber());
        room.setType(roomDto.getType());
        return room;
    }
}
