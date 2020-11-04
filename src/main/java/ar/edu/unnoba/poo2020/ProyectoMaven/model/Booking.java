

package ar.edu.unnoba.poo2020.ProyectoMaven.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Booking")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guest")
    private User guest;

    @Column(name = "check_in")
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "room")
    private Room room;

    @Column(name = "breakfast_included")
    private boolean breakfastInclude;

    @Column(name = "parking")
    private boolean parking;

    @Column(name = "free_cancelation")
    private boolean freeCancelation;

    @Column(name = "cost")
    private Float cost;


    //Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isBreakfastInclude() {
        return breakfastInclude;
    }

    public void setBreakfastInclude(boolean breakfastInclude) {
        this.breakfastInclude = breakfastInclude;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isFreeCancelation() {
        return freeCancelation;
    }

    public void setFreeCancelation(boolean freeCancelation) {
        this.freeCancelation = freeCancelation;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }


}
