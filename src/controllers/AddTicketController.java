package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.*;

import java.io.FileInputStream;
import java.util.*;


public class AddTicketController {
    Stage stage;
    Ticket ticket;
    Movie movie;
    Seat seat;
    @FXML
    TextField customerPhoneNumber;
    @FXML
    ComboBox chooseMovie, currentShowtime, chooseRow, chooseSeatNum;
    @FXML
    ImageView imageView;

    public void start(Stage stage) {
        this.stage = stage;
        ticket = new Ticket();
    }

    public void getTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void populateValues() throws Exception {
        List<Movie> movies = AccessingMovieDatabase.retrieveAllRecords();
        for (int i = 0; i < movies.size(); i++) {
            chooseMovie.getItems().add(movies.get(i).getName());
        }
    }

    public void populateValues2(String name) throws Exception {
        movie = new Movie();
        movie.setName(name);
        movie = AccessingMovieDatabase.retrieveOneRecord(movie);
        currentShowtime.getItems().add(movie.getTimeSlot());
    }

    public ImageView populateValues3() throws Exception {
        List<Seat> seats = AccessingSeatDatabase.retrieveAllRecords();
        FileInputStream file1 = new FileInputStream("D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\image\\FixedSeat.jpg");
        FileInputStream file2 = new FileInputStream("D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\image\\FullSeat.jpg");
        Image img1 = new Image(file1);
        Image img2 = new Image(file2);
        if (seats.size() == 48) {
            imageView.setImage(img1);
        } else
            imageView.setImage(img2);
        for (int i = 0; i < 5; i++) {
            if (i == 4 & !AccessingSeatDatabase.isExistSeat())
                break;
            chooseRow.getItems().add(Character.toString(i + 65));
        }
        return imageView;
    }

    public void getSeatRowChoice() throws Exception {
        try {
            String seatRow = chooseRow.getSelectionModel().getSelectedItem().toString();
            chooseSeatNum.getItems().clear();
            for (int i = 0; i < 12; i++) {
                if (!AccessingTicketDatabase.isExistSeat(seatRow + (i + 1)))
                    chooseSeatNum.getItems().add(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public String getMovieChoice() throws Exception {
        String movieName;
        movieName = chooseMovie.getSelectionModel().getSelectedItem().toString();
        currentShowtime.getItems().clear();
        populateValues2(movieName);
        return movieName;
    }

    public void goNext() throws Exception {
        try {
            String userInput = customerPhoneNumber.getText();
            if (Validation.isValidId(userInput))
                ticket.setCustomerPhoneNumber(userInput);
            else
                throw new MyException("Invalid input");
            String file = "../gui/AddTicket2.fxml";
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(file));
            AddTicketController ctrl = new AddTicketController();
            loader.setController(ctrl);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            ctrl.start(stage);
            ctrl.populateValues();
            ctrl.getTicket(ticket);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            //reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void goNext2() throws Exception {
        try {
            String movieName = chooseMovie.getSelectionModel().getSelectedItem().toString();
            String showtime = currentShowtime.getSelectionModel().getSelectedItem().toString();
            movie = new Movie();
            movie.setName(movieName);
            movie = AccessingMovieDatabase.retrieveOneRecord(movie);
            ticket.setMovie(movie);
            String file = "../gui/AddTicket3.fxml";
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(file));
            AddTicketController ctrl = new AddTicketController();
            loader.setController(ctrl);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            ctrl.start(stage);
            ctrl.getTicket(ticket);
            ctrl.populateValues3();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            //reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void add() throws Exception {
        try {
            String seatRow = chooseRow.getSelectionModel().getSelectedItem().toString();
            String seatNumber = chooseSeatNum.getSelectionModel().getSelectedItem().toString();
            if (!Validation.isValidCharacter(seatRow) & !Validation.isValidId(seatNumber))
                throw new MyException("Invalid input");
            seat = new Seat();
            seat.setSeatRow(seatRow);
            seat.setSeatNumber(Integer.parseInt(seatNumber));
            AccessingSeatDatabase.retrieveOneRecord(seat);
            ticket.setSeat(seat);

            ticket.setDate();
            ticket.setId();
            ticket.setPrice();
            int rs = AccessingTicketDatabase.insertNewRecords(ticket);
            if (rs > 0) {
                MsgDialog.displayInformationDialog("Information", ticket.getId() + " inserted successfully");

            } else
                MsgDialog.displayErrorDialog("Error", "Error while inserting " + ticket.getId());
        } catch (Exception e) {
            e.printStackTrace();
            //reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void exit() {
        stage.close();
    }

    public void goBack() throws Exception {
        String file = "../gui/AddTicket.fxml";
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(file));
        AddTicketController ctrl = new AddTicketController();
        loader.setController(ctrl);
        stage.setScene(new Scene(loader.load()));
        ctrl.start(stage);
        stage.show();
    }
}
