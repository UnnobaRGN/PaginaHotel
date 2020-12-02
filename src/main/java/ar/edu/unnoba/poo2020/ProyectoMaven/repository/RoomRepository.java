package ar.edu.unnoba.poo2020.ProyectoMaven.repository;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query("SELECT r FROM Room r WHERE r.occupancy>= :occupancy and r.availability >("+
            "SELECT COUNT(b) FROM Booking b WHERE b.room = r and b.checkIn between :checkIn and :checkOut) ORDER BY (r.occupancy)")

    List<Room> getRoomsAvailable(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut, @Param("occupancy") int occupancy);

    @Query("Select r From Room r where r.id = :roomId and r.availability > (" +
            "Select count(b) From Booking b where b.room = r and b.checkIn between :checkIn and :checkOut)")
    Room isRoomAvailable(@Param("checkIn")Date checkIn, @Param("checkOut") Date checkOut, @Param("roomId") Long roomId);
}
