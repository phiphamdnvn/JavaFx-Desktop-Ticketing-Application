package models;

import controllers.Validation;

import java.util.*;

public class Ticket {
    private String id;
    private double price;
    private String customerPhoneNumber;
    private String date;
    private String seat2;
    public Movie movie;
    public Seat seat;
    int result = 0;

    //This constructor only for retrieve data from sql purpose
    public Ticket(String id, double price, String customerPhoneNumber, String date) {
        setId(id);
        this.price = price;
        this.customerPhoneNumber = customerPhoneNumber;
        this.date = date;
    }

    public Ticket() {
        movie = new Movie();
        seat = new Seat();
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = seat.getSeatRow() + seat.getSeatNumber() + movie.getId() + customerPhoneNumber;
    }

    public void setId(String id){
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = 12.9 + seat.getPrice();
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public int setCustomerPhoneNumber(String customerPhoneNumber) {
        if (Validation.isValidId(customerPhoneNumber)) {
            this.customerPhoneNumber = customerPhoneNumber;
            result = 1;
        }
        return result;
    }

    public void setDate() {
        date = Calendar.getInstance().getTime().toString();
    }

    public String getDate() {
        return date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getSeat2() {
        return seat2;
    }

    public void setSeat2(String seat2) {
        this.seat2 = seat2;
    }

}
