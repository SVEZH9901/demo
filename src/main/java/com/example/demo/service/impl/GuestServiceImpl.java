package com.example.demo.service.impl;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.GuestReservationsResponse;
import com.example.demo.dto.GuestsResponse;
import com.example.demo.exception.GuestNotFoundException;
import com.example.demo.mapper.GuestMapper;
import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public GuestDto createGuest(Guest guest) {
        Guest newGuest = guestRepository.save(guest);
        return GuestMapper.mapToDto(newGuest);
    }

    @Override
    public GuestsResponse getAllGuests(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Guest> guests = guestRepository.findAll(pageable);
        List<Guest> listOfGuests = guests.getContent();
        List<GuestDto> content = listOfGuests.stream().map(GuestMapper::mapToDto).collect(Collectors.toList());

        GuestsResponse guestsResponse = new GuestsResponse();
        guestsResponse.setContent(content);
        guestsResponse.setPageNo(guests.getNumber());
        guestsResponse.setPageSize(guests.getSize());
        guestsResponse.setTotalElements(guests.getTotalElements());
        guestsResponse.setTotalPages(guests.getTotalPages());
        guestsResponse.setLast(guests.isLast());

        return guestsResponse;
    }

    @Override
    public GuestDto getGuestById(int id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest could not be found"));
        return GuestMapper.mapToDto(guest);
    }

    @Override
    public GuestDto updateGuest(GuestDto guestDto, int id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest could not be found"));

        guest.setName(guestDto.getName());
        guest.setLastName(guestDto.getLastName());
        guest.setMiddleName(guestDto.getMiddleName());
        guest.setCourse(guest.getCourse());

        Guest updatedGuest = guestRepository.save(guest);

        return GuestMapper.mapToDto(updatedGuest);
    }

    @Override
    public void deleteGuestById(int id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest could not be found"));
        guestRepository.delete(guest);
    }

    @Override
    public GuestReservationsResponse getGuestReservations(int id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest could not be found"));
        return GuestMapper.mapToGuestReservationsResponse(guest);
    }
}
