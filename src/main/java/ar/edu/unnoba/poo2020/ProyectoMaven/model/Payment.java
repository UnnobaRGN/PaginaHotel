
package ar.edu.unnoba.poo2020.ProyectoMaven.model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date createdAt; //borrar tablas e iniciar de vuelta el programa

    @NotNull
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String card;

    private String cardNumber;


    //Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
