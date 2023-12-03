package com.example.demo.dto;

import lombok.Data;

@Data
public class ReservationDto {
    private int id;
    private String title;
    private String dateStart;
    private String dateEnd;
    private RoomDto room;
    private GuestDto guest;
}
