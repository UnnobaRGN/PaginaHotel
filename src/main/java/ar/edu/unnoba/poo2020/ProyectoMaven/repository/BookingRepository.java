package ar.edu.unnoba.poo2020.ProyectoMaven.repository;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("SELECT b From Booking b WHERE b.guest.id= :Id ORDER BY b.checkIn DESC")
    List<Booking> getBookingById(@Param("Id") Long id);



}
