package ar.edu.unnoba.poo2020.ProyectoMaven.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmBookingRequestDTO {

    private Long roomId;
    private String checkIn;
    private String checkOut;
    private int occupancy;
    private boolean desayuno;
    private boolean cochera;
    private boolean cancelarGratis;
    // tener en cuenta para el final: desayuno, cochera,cancelacion
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String getCheckIn() {
        return checkIn;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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


    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    public boolean isCochera() {
        return cochera;
    }

    public void setCochera(boolean cochera) {
        this.cochera = cochera;
    }

    public boolean isCancelarGratis() {
        return cancelarGratis;
    }

    public void setCancelarGratis(boolean cancelarGratis) {
        this.cancelarGratis = cancelarGratis;
    }
}
