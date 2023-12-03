package com.example.demo.service.impl;

import com.example.demo.dto.ReservationDto;
import com.example.demo.dto.ReservationsResponse;
import com.example.demo.exception.GuestNotFoundException;
import com.example.demo.exception.ReservationNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.Guest;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto, int guestId, int roomId) {
        Reservation reservation = ReservationMapper.mapToEntity(reservationDto);

        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException("Guest could not be found"));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException("Room could not be found"));

        reservation.setGuest(guest);
        reservation.setRoom(room);

        Reservation newReservation = reservationRepository.save(reservation);
        return ReservationMapper.mapToDto(newReservation);
    }

    @Override
    public ReservationsResponse getAllReservations(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Reservation> reservations = reservationRepository.findAll(pageable);
        List<Reservation> listOfReservations = reservations.getContent();
        List<ReservationDto> content = listOfReservations.stream().map(ReservationMapper::mapToDto).collect(Collectors.toList());

        ReservationsResponse reservationsResponse = new ReservationsResponse();
        reservationsResponse.setContent(content);
        reservationsResponse.setPageNo(reservations.getNumber());
        reservationsResponse.setPageSize(reservations.getSize());
        reservationsResponse.setTotalElements(reservations.getTotalElements());
        reservationsResponse.setTotalPages(reservations.getTotalPages());
        reservationsResponse.setLast(reservations.isLast());

        return reservationsResponse;
    }

    @Override
    public ReservationDto getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation could not be found"));
        return ReservationMapper.mapToDto(reservation);
    }

    @Override
    public ReservationDto updateReservation(ReservationDto reservationDto, int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation could not be found"));

        LocalDate dateStart = LocalDate.parse(reservationDto.getDateStart());
        LocalDate dateEnd = LocalDate.parse(reservationDto.getDateEnd());

        reservation.setTitle(reservationDto.getTitle());
        reservation.setDateStart(dateStart);
        reservation.setDateEnd(dateEnd);

        Reservation updatedReservation = reservationRepository.save(reservation);

        return ReservationMapper.mapToDto(updatedReservation);
    }

    @Override
    public void deleteReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation could not be found"));
        reservationRepository.delete(reservation);
    }

}
