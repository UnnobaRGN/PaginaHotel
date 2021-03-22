package ar.edu.unnoba.poo2020.ProyectoMaven.controller;

import ar.edu.unnoba.poo2020.ProyectoMaven.DTO.*;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Payment;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.User;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IBookingService;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IPaymentService;
import ar.edu.unnoba.poo2020.ProyectoMaven.service.IRoomService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Bookings")
@SessionAttributes("Booking2")
public class BookingController {
    private IRoomService roomService;
    private IBookingService bookingService;
    private ModelMapper modelMapper;
    private IPaymentService paymentService;

    @Autowired
    public BookingController(IRoomService roomService, IBookingService bookingService,ModelMapper modelMapper,IPaymentService paymentService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
        this.paymentService = paymentService;
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
    public String getRoomsAvailable(@ModelAttribute RoomsAvailabilityDTO roomsAvailabilityDTO, Model model,Authentication auth) throws ParseException {
        if(bookingService.VerificarFechas(roomsAvailabilityDTO.getCheckInDateConverted(),roomsAvailabilityDTO.getCheckOutDateConverted())) {
            if(auth != null) {
                User u = (User) auth.getPrincipal();
                model.addAttribute("firstName", u.getFirstName());
                model.addAttribute("lastName", u.getLastName());
            }
            model.addAttribute("roomsAvailability", new RoomsAvailabilityDTO());
            model.addAttribute("rooms",new ArrayList<RoomDTO>());
            model.addAttribute("mensaje","");
            return "Bookings/availability";

        }else{
            List<Room> rooms = new ArrayList<>();
            try {
                rooms = roomService.getRoomsAvailable(
                        roomsAvailabilityDTO.getCheckInDateConverted(),
                        roomsAvailabilityDTO.getCheckOutDateConverted(),
                        roomsAvailabilityDTO.getOccupancy());

            } catch (Exception e) {
            }
            List<RoomDTO> roomDTO = rooms.stream()
                    .map(room -> modelMapper.map(room, RoomDTO.class))
                    .collect(Collectors.toList());
            if (auth != null) {
                User u = (User) auth.getPrincipal();
                model.addAttribute("firstName", u.getFirstName());
                model.addAttribute("lastName", u.getLastName());
            }
            model.addAttribute("rooms", roomDTO);
            model.addAttribute("roomsAvailability", roomsAvailabilityDTO);
            model.addAttribute("Booking", new NewBookingRequestDTO());
            return "Bookings/availability";
        }
    }

    @PostMapping("/new")
    public String newBooking(@ModelAttribute NewBookingRequestDTO newBookingRequestDTO, Model model, Authentication auth){
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
    public String confirmBooking(@ModelAttribute ConfirmBookingRequestDTO confirmBookingRequestDTO, Model model, Authentication auth, @RequestParam(value="Desayuno",required = false) String checkboxDesayuno, @RequestParam(value="Cochera",required = false) String checkboxCochera, @RequestParam(value="Cancelacion",required = false) String checkboxCancelacionGratis) throws Exception {
        //Convertir DTO a un objeto de modelo
        Booking booking = modelMapper.map(confirmBookingRequestDTO, Booking.class);
        booking.setGuest((User) auth.getPrincipal());
        booking.setId(null);
        if(checkboxDesayuno!=null) {
            booking.setBreakfastIncluded(true);
        }
        if(checkboxCochera!=null){
            booking.setParking(true);
        }
        if(checkboxCancelacionGratis!=null){
            booking.setFreeCancelation(true);
        }
        //delegacion de confirmacion a un servicio y en funcion de la respuesta redireciconar
        try{
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
            model.addAttribute("Booking2",booking);
            model.addAttribute("payment",new PaymentDTO());
            return "Bookings/payment";
        }catch (Exception e){
            return "Bookings/availability";
        }

    }

    @PostMapping("/pago")
    public String realizarPago(@ModelAttribute PaymentDTO paymentDTO, Model model, @ModelAttribute("Booking2")Booking booking,Authentication auth) throws Exception {
        try {
            Booking booking1 = bookingService.newBooking(booking);
            paymentDTO.setIdbooking(booking1.getId());
            Payment p = modelMapper.map(paymentDTO, Payment.class);
            p.setBooking(booking1);
            paymentService.savePayment(p);
            if(auth != null) {
                User u = (User) auth.getPrincipal();
                model.addAttribute("firstName", u.getFirstName());
                model.addAttribute("lastName", u.getLastName());
            }
            return "Bookings/confirmed";
        }catch (Exception e){
            if(auth != null) {
                User u = (User) auth.getPrincipal();
                model.addAttribute("firstName", u.getFirstName());
                model.addAttribute("lastName", u.getLastName());
            }
            return "error/index";
        }
    }

    @GetMapping("/ConsultaReservas")
    public String consultarReservas(Model model,Authentication auth){
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
            List<Booking> Reservabookings = bookingService.listarReservas(u.getId());
            model.addAttribute("listaReservas",Reservabookings);
        }
        return "Bookings/consultarReserva";
    }


    @GetMapping("/{id}/detalles")
    public String verDetalles(@PathVariable Long id,Model model,Authentication auth){
        if(auth != null) {
            User u = (User) auth.getPrincipal();
            model.addAttribute("firstName", u.getFirstName());
            model.addAttribute("lastName", u.getLastName());
            Booking booking=bookingService.findBooking(id);
            model.addAttribute("BookingDetalles",booking);
        }
        return "Bookings/detalles";

    }









    @GetMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id, Model model){
        bookingService.delete(id);
        return "redirect:/consultaReserva";

    }

}
