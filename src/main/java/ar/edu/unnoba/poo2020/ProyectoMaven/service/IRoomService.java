package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRoomService {
    List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy);
    Optional<Room> findby(Long roomId);
    public List<Room> listar();
}
