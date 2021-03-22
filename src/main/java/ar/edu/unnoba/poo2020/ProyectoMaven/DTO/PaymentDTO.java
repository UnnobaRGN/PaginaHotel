package ar.edu.unnoba.poo2020.ProyectoMaven.DTO;



public class PaymentDTO {
    private Long idbooking;
    private String card;
    private String cardNumber;

    public Long getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Long idbooking) {
        this.idbooking = idbooking;
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
