package ar.edu.unnoba.poo2020.ProyectoMaven.repository;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
