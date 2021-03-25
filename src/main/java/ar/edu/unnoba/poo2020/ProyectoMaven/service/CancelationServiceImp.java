package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Cancellation;
import ar.edu.unnoba.poo2020.ProyectoMaven.repository.CancellationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CancelationServiceImp implements ICancelationService{

    @Autowired
    private CancellationRepository cancellationRepository;


    @Override
    public Cancellation cancelarReserva(Cancellation c) {
        c.setCreatedAt(new Date());
        return cancellationRepository.save(c);
    }

    @Override
    public Cancellation findCancellation(Long id) {
        return cancellationRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        cancellationRepository.deleteById(id);
    }

    @Override
    public Cancellation findCancellationByBooking(Long id) {
        return cancellationRepository.findByBookingId(id);
    }

    @Override
    public void deleteAll() {
        cancellationRepository.deleteAll();
    }

}
