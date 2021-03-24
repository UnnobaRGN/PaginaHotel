package ar.edu.unnoba.poo2020.ProyectoMaven.repository;


import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("SELECT p FROM Payment p WHERE p.booking.id=:Id")
    Payment getPaymentById(@Param("Id") Long id);


}
