package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Payment;
import ar.edu.unnoba.poo2020.ProyectoMaven.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImp implements IPaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(Payment p) {
        p.setCreatedAt(new Date());
        return paymentRepository.save(p);

    }

    @Override
    public void deletePayment(Long p) {
        paymentRepository.deleteById(p);
    }

    @Override
    public Payment findByBookingId(Long id) {
        return paymentRepository.getPaymentById(id);
    }
}