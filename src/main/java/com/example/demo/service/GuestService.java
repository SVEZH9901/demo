package com.example.demo.service;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.GuestReservationsResponse;
import com.example.demo.dto.GuestsResponse;
import com.example.demo.model.Guest;

public interface GuestService {
    GuestDto createGuest(Guest guest);
    GuestsResponse getAllGuests(int pageNo, int pageSize);
    GuestDto getGuestById(int id);
    GuestDto updateGuest(GuestDto guestDto, int id);
    void deleteGuestById(int id);

    GuestReservationsResponse getGuestReservations(int id);
}
