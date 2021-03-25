package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Cancellation;

import java.util.List;

public interface ICancelationService {

    Cancellation cancelarReserva(Cancellation cancellation);
    Cancellation findCancellation(Long id);
    void delete(Long id);
    Cancellation findCancellationByBooking(Long id);
    void deleteAll();
}
