package ar.edu.unnoba.poo2020.ProyectoMaven.model;

import javax.persistence.*;

@Entity
@Table(name="Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float price;
    private int occupancy;
    private int availability;
    private String facilities;



}
