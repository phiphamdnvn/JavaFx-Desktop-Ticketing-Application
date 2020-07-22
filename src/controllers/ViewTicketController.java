package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Movie;
import models.Ticket;

import java.util.*;

public class ViewTicketController {
    Stage stage;
    Ticket ticket;
    Movie movie;
    @FXML
    ComboBox chooseMovie;
    @FXML
    TableView table;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> seatCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> phoneCol;

    public void getStared(Stage stage) {
        this.stage = stage;
        ticket = new Ticket();
    }

    public void populateValue() throws Exception {
        List<Movie> movies = AccessingMovieDatabase.retrieveAllRecords();
        for (Movie mv : movies) {
            chooseMovie.getItems().add(mv.getName());
        }
    }

    public void loadData() throws Exception{
        table.getItems().clear();
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        seatCol.setCellValueFactory(new PropertyValueFactory("seat2"));
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("customerPhoneNumber"));
        movie = new Movie();
        movie.setName(chooseMovie.getSelectionModel().getSelectedItem().toString());
        List<Ticket> tickets = AccessingTicketDatabase.retrieveAllRecords(movie);
        for(Ticket tk: tickets)
            table.getItems().add(tk);
    }
}
