package ar.edu.unnoba.poo2020.ProyectoMaven.repository;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CancellationRepository extends JpaRepository<Cancellation,Long> {

    @Query("SELECT c From Cancellation c WHERE c.booking.id= :Id ")
    Cancellation findByBookingId(@Param("Id") Long id);
}
