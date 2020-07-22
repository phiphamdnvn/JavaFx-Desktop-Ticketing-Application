package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import models.Movie;
import models.Ticket;

import java.util.List;

public class DeleteTicketController {
    Stage stage;
    Ticket ticket;
    Movie movie;
    @FXML
    ComboBox chooseTicket, chooseMovie;

    public void getMovie(Movie movie) {
        this.movie = movie;
    }

    public void populateValue(Stage stage) throws Exception {
        this.stage = stage;
        List<Movie> movies = AccessingMovieDatabase.retrieveAllRecords();
        for (Movie mv : movies)
            chooseMovie.getItems().add(mv.getName());
    }

    public void chooseMovie() throws Exception {
        movie = new Movie();
        movie.setName(chooseMovie.getSelectionModel().getSelectedItem().toString());
        List<Ticket> tickets = AccessingTicketDatabase.retrieveAllRecords(movie);
        chooseTicket.getItems().clear();
        for (Ticket tk : tickets)
            chooseTicket.getItems().add(tk.getId());
    }

    public void delete() throws Exception {
        try {
            String id = chooseTicket.getSelectionModel().getSelectedItem().toString();
            int rs = AccessingTicketDatabase.deleteTicket(id);
            if (rs > 0) {
                MsgDialog.displayInformationDialog("Information", "Ticket " + id + " deleted successfully");
                stage.close();
            } else
                MsgDialog.displayErrorDialog("Error", "Error while deleting " + "Ticket " + id);
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
