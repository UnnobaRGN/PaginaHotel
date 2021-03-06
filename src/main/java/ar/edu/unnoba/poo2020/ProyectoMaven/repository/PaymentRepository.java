package ar.edu.unnoba.poo2020.ProyectoMaven.repository;


import ar.edu.unnoba.poo2020.ProyectoMaven.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
