package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Movie;
import models.Seat;
import models.Ticket;

import java.util.List;

public class UpdateTicketController {
    Stage stage;
    Movie movie;
    Ticket ticket;
    Seat seat;
    @FXML
    ComboBox chooseMovie, chooseTicket, seatRow, seatNumber;
    @FXML
    TextField currentSeat;

    public void getStarted(Stage stage) {
        this.stage = stage;
    }

    public void getTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void populateValue() throws Exception {
        List<Movie> movies = AccessingMovieDatabase.retrieveAllRecords();
        for (Movie mv : movies)
            chooseMovie.getItems().add(mv.getName());
    }

    public void populateValue2() throws Exception {
        for (int i = 0; i < 5; i++) {
            seatRow.getItems().add(Character.toString(i + 65));
            if (i == 3 & !AccessingSeatDatabase.isExistSeat())
                break;
        }
        AccessingTicketDatabase.retrieveOneRecords(ticket);
        currentSeat.setText(ticket.getSeat2());
    }

    public void getSeatNumber(String newRow) throws Exception {
        for (int i = 0; i < 12; i++) {
            if (!AccessingTicketDatabase.isExistSeat(newRow + (i + 1)))
                seatNumber.getItems().add(i + 1);
        }
    }

    public void chooseNewRow() throws Exception {
        String newRow = seatRow.getSelectionModel().getSelectedItem().toString();
        seatNumber.getItems().clear();
        getSeatNumber(newRow);
    }

    public void chooseMovie() throws Exception {
        movie = new Movie();
        movie.setName(chooseMovie.getSelectionModel().getSelectedItem().toString());
        ticket = new Ticket();
        ticket.setMovie(movie);
        List<Ticket> tickets = AccessingTicketDatabase.retrieveAllRecords(movie);
        chooseTicket.getItems().clear();
        for (Ticket tk : tickets)
            chooseTicket.getItems().add(tk.getId());
    }

    public void goNext() throws Exception {
        try {
            String id = chooseTicket.getSelectionModel().getSelectedItem().toString();
            ticket.setId(id);

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../gui/UpdateTicket2.fxml"));
            UpdateTicketController ctrl = new UpdateTicketController();
            loader.setController(ctrl);
            stage.setScene(new Scene(loader.load()));
            ctrl.getStarted(stage);
            ctrl.getTicket(ticket);
            ctrl.populateValue2();
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

    public void update() throws Exception {
        try {
            String newSeat = seatRow.getSelectionModel().getSelectedItem().toString() + seatNumber.getSelectionModel().getSelectedItem().toString();
            movie = AccessingMovieDatabase.retrieveOneRecord(ticket.getMovie());
            seat = new Seat();
            seat.setSeatRow(Character.toString(newSeat.charAt(0)));
            seat.setSeatNumber(Integer.parseInt(newSeat.substring(1)));
            AccessingSeatDatabase.retrieveOneRecord(seat);
            ticket.setSeat(seat);
            ticket.setMovie(movie);
            ticket.setPrice();
            ticket.setId();
            int rs = AccessingTicketDatabase.updateTicket(ticket, newSeat);
            if (rs > 0) {
                MsgDialog.displayInformationDialog("Information", ticket.getSeat2() + " updated successfully to new seat " + newSeat);
                stage.close();
            } else
                MsgDialog.displayErrorDialog("Error", "Error while updating " + ticket.getId());
        } catch (Exception e) {
            e.printStackTrace();
            //reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void exit(){
        stage.close();
    }
}
