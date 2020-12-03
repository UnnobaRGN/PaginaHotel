package ar.edu.unnoba.poo2020.ProyectoMaven.controller;

import ar.edu.unnoba.poo2020.ProyectoMaven.DTO.*;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.User;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IBookingService;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IRoomService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Bookings")
public class BookingController {
    private IRoomService roomService;
    private IBookingService bookingService;
    private ModelMapper modelMapper;

    @Autowired
    public BookingController(IRoomService roomService, IBookingService bookingService,ModelMapper modelMapper) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/availability")
    public String roomsAvailability(Model model, Authentication auth){
        model.addAttribute("roomsAvailability", new RoomsAvailabilityDTO());
        model.addAttribute("rooms",new ArrayList<RoomDTO>());
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
        }
        return "Bookings/availability";
    }

    @PostMapping("/availability")
    public String getRoomsAvailable(@ModelAttribute RoomsAvailabilityDTO roomsAvailabilityDTO, Model model,Authentication auth){
        List<Room> rooms=new ArrayList<>();
        try {
            rooms = roomService.getRoomsAvailable(
                roomsAvailabilityDTO.getCheckInDateConverted(),
                roomsAvailabilityDTO.getCheckOutDateConverted(),
                roomsAvailabilityDTO.getOccupancy());

        }catch (Exception e){}
        List<RoomDTO> roomDTO = rooms.stream()
                .map(room -> modelMapper.map(room,RoomDTO.class))
                .collect(Collectors.toList());
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
        }
        model.addAttribute("rooms",roomDTO);
        model.addAttribute("roomsAvailability",roomsAvailabilityDTO);
        model.addAttribute("Booking", new NewBookingRequestDTO());
        return "Bookings/availability";
    }

    @PostMapping("/new")
    public String newBooking(@ModelAttribute NewBookingRequestDTO newBookingRequestDTO, NewBookingResponseDTO newBookingResponseDTO, Model model, Authentication auth){
        NewBookingResponseDTO booking = new NewBookingResponseDTO();
        RoomDTO roomDTO = modelMapper.map(roomService.findby(newBookingRequestDTO.getRoomId()).get(), RoomDTO.class);
        booking.setRoomDTO(roomDTO);
        booking.setCheckIn(newBookingRequestDTO.getCheckIn());
        booking.setCheckOut(newBookingRequestDTO.getCheckOut());
        booking.setOccupancy(newBookingRequestDTO.getOccupancy());
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
        }
        model.addAttribute("Booking",booking);
        return "Bookings/new";
    }

    @PostMapping("/confirm")
    public String confirmBooking(@ModelAttribute ConfirmBookingRequestDTO confirmBookingRequestDTO, Model model,Authentication auth) throws Exception {
        //Convertir DTO a un objeto de modelo
        Booking booking = modelMapper.map(confirmBookingRequestDTO, Booking.class);
        booking.setGuest((User) auth.getPrincipal());
        booking.setId(null);
        //delegacion de confirmacion a un servicio y en funcion de la respuesta redireciconar
        try{
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
            bookingService.newBooking(booking);
            return "Bookings/confirmed";
        }catch (Exception e){
            return "Bookings/availability";
        }

    }

}
