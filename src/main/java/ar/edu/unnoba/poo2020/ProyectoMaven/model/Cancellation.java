
package ar.edu.unnoba.poo2020.ProyectoMaven.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="Cancellations")

public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date createdAt;

    @OneToMany(mappedBy = "cancelattion")
    private List<Booking> booking;


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
}
