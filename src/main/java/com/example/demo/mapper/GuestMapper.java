package com.example.demo.mapper;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.GuestReservationsResponse;
import com.example.demo.dto.ReservationInfo;
import com.example.demo.model.Guest;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GuestMapper {
    public static GuestDto mapToDto(Guest guest){
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setName(guest.getName());
        guestDto.setLastName(guest.getLastName());
        guestDto.setMiddleName(guest.getMiddleName());
        guestDto.setEducation(guestDto.getEducation());
        return guestDto;
    }

    public static GuestReservationsResponse mapToGuestReservationsResponse(Guest guest){
        GuestReservationsResponse guestReservationsResponse = new GuestReservationsResponse();

        List<ReservationInfo> reservationsInfo = ReservationMapper.mapElementsToReservationInfo(guest.getReservations());

        guestReservationsResponse.setId(guest.getId());
        guestReservationsResponse.setName(guest.getName());
        guestReservationsResponse.setLastName(guest.getLastName());
        guestReservationsResponse.setReservations(reservationsInfo);

        return guestReservationsResponse;
    }

}
