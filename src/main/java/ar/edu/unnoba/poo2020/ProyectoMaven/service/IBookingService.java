package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;


import java.util.Date;
import java.util.List;

public interface IBookingService {
    Booking newBooking(Booking booking) throws Exception;
    public List<Booking> listarReservas(Long id);
    public  Booking findBooking(Long id);
    public void delete(Long id);
    boolean VerificarFechas(Date checkin, Date checkout);
    boolean VerificarCancelacion(Date checkIn);
}
