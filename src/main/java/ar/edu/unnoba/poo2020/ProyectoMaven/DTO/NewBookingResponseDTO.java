package ar.edu.unnoba.poo2020.ProyectoMaven.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewBookingResponseDTO {
        private RoomDTO roomDTO;
        private String checkIn;
        private String checkOut;
        private int occupancy;

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        public String getCheckIn() {
            return checkIn;
        }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public void setCheckIn(String checkIn) {
            this.checkIn = checkIn;
        }

        public String getCheckOut() {
            return checkOut;
        }

        public void setCheckOut(String checkOut) {
            this.checkOut = checkOut;
        }

        public int getOccupancy() {
            return occupancy;
        }

        public void setOccupancy(int occupancy) {
            this.occupancy = occupancy;
        }

        public Date getCheckInDateConverted() throws ParseException {
            return dateFormat.parse(this.checkIn);
        }

        public Date getCheckOutDateConverted() throws ParseException{
            return dateFormat.parse(this.checkOut);
        }

        public void setCheckOutDate(Date date){
            this.checkIn=dateFormat.format(date);
        }




}
