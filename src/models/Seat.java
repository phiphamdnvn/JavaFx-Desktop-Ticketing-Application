package models;

import controllers.Validation;

public class Seat {
    private String status;
    private String vipSeat;
    private int seatNumber;
    private String seatRow;
    private double price;

    public Seat(String vipSeat, String seatRow,  int seatNumber) {
        setVipSeat(vipSeat);
        setSeatRow(seatRow);
        setSeatNumber(seatNumber);
        setStatus();
        setPrice();
    }

    public Seat() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        if ((int) seatRow.charAt(0) > 'D')
            status = "extended";
        else
            status = "fixed";
    }

    public String getVipSeat() {
        return vipSeat;
    }

    public void setVipSeat(String vipSeat) {
        this.vipSeat = vipSeat;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        if(Validation.isValidId(Integer.toString(seatNumber)))
            this.seatNumber = seatNumber;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        price = 2.24;
        if (status.equalsIgnoreCase("extended")) {
            if (vipSeat.equalsIgnoreCase("yes"))
                price = (price + 1.0) * 2;
            else
                price *= 2;
        } else if (vipSeat.equalsIgnoreCase("yes"))
            price += 1;
    }
}
