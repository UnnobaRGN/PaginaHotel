package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Payment;

import java.util.List;

public interface IPaymentService {
    public Payment savePayment(Payment p);
    public void deletePayment(Long p);
    public Payment findByBookingId(Long id);
}
