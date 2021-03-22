package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.Booking;
import ar.edu.unnoba.poo2020.ProyectoMaven.model.Room;
import ar.edu.unnoba.poo2020.ProyectoMaven.repository.BookingRepository;
import ar.edu.unnoba.poo2020.ProyectoMaven.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImp implements IBookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Booking newBooking(Booking booking) throws Exception {
        booking.setRoom(roomRepository.findById(booking.getRoom().getId()).get());
        booking.setCost(calcularCostoTotal(booking));
        booking.setCreatedAt(new Date());
         Room room = roomRepository.isRoomAvailable(booking.getCheckIn(),booking.getCheckOut(),booking.getRoom().getId());
         if(room!=null) {
             return booking = bookingRepository.save(booking);
         }else{
             throw new Exception("");
         }
    }

    @Override
    public List<Booking> listarReservas(Long id) {
        return bookingRepository.getBookingById(id);
    }

    @Override
    public Booking findBooking(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public boolean VerificarFechas(Date checkin, Date checkout) {
        return checkin.before(new Date()) || checkin.after(checkout);

    }


    public float calcularCostoTotal(Booking booking){
        float total=booking.getRoom().getPrice() ;
        if(booking.isBreakfastIncluded()){
            total+=100;
        }
        if(booking.isFreeCancelation()){
            total+=200;
        }
        if(booking.isParking()){
            total+=300;
        }
        return total;
    }
}
