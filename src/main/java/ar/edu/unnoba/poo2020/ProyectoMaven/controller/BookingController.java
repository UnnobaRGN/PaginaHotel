package ar.edu.unnoba.poo2020.ProyectoMaven.controller;

import ar.edu.unnoba.poo2020.ProyectoMaven.DTO.RoomDTO;
import ar.edu.unnoba.poo2020.ProyectoMaven.DTO.RoomsAvailabilityDTO;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IBookingService;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IRoomService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
    public String roomsAvailability(Model model){
        model.addAttribute("roomsAvailability", new RoomsAvailabilityDTO());
        model.addAttribute("rooms",new ArrayList<RoomDTO>());
        return "Bookings/availability";
    }

    @PostMapping("/availability")
    public String getRoomsAvailable(@ModelAttribute RoomsAvailabilityDTO roomsAvailabilityDTO, Model model){
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
        model.addAttribute("rooms",roomDTO);
        model.addAttribute("roomsAvailability",roomsAvailabilityDTO);
        return "Bookings/availability";
    }

}
