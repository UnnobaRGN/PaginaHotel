package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;
import ar.edu.unnoba.poo2020.ProyectoMaven.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class RoomServiceImp implements  IRoomService {

    private RoomRepository roomRepository;
    @Autowired
    public RoomServiceImp(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy) {
        return roomRepository.getRoomsAvailable(checkIn,checkOut,occupancy);
    }

    @Override
    public Optional<Room> findby(Long roomId) {
        return roomRepository.findById(roomId);

    }

    @Override
    public List<Room> listar() {
        return roomRepository.findAll();
    }

}
