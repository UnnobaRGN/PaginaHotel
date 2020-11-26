package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;

public interface IBookingService {
    Booking newBooking(Booking booking) throws Exception;
}
