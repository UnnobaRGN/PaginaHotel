package ar.edu.unnoba.poo2020.ProyectoMaven.controller;

import ar.edu.unnoba.poo2020.ProyectoMaven.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private IRoomService roomService;
    private IBookingService bookingService;
    private IModelMapper modelMapper;

    @Autowired
    public BookingController(IRoomService roomService, IBookingService bookingService, IModelMapper modelMapper) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/availability")
    public String roomsAvailability(Model model){
        model.addAttribute("roomsAvailability", new RoomsAvailabilityDTO());
        model.addAttribute("rooms",new ArrayList<RoomDTO>());
        return "bookings/availability";
    }


}
