package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Movie;

import java.util.List;

public class UpdateMovieController {
    @FXML
    TextField newShowtime;
    @FXML
    ComboBox chooseMovie, currentShowtime;
    Stage stage;
    Movie movie;

    public void populateValues(Stage stage) throws Exception {
        this.stage = stage;
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

    public String getMovieChoice() throws Exception {
        String movieName = "";
        try {
            movieName = chooseMovie.getSelectionModel().getSelectedItem().toString();
            currentShowtime.getItems().clear();
            populateValues2(movieName);
        } catch (Exception e) {
            e.printStackTrace();
            reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
        return movieName;
    }

    public void update() throws Exception {
        try {
            String movieName, newShowtime;
            newShowtime = this.newShowtime.getText();
            if (newShowtime.equalsIgnoreCase("24:00"))
                newShowtime = "00:00";
            movieName = getMovieChoice();
            movie = new Movie();
            movie.setName(movieName);
            movie.setTimeSlot(newShowtime);
            if(AccessingMovieDatabase.isExistedMovie(movie) == 1)
                throw new MyException("This showtime is already occupied");
            if (movie.getName() != null & movie.getTimeSlot() != null
                    & Validation.isValidShowTime(newShowtime))
            {
                if (!AccessingTicketDatabase.isExistMovie(movie)) {
                    int rs = AccessingMovieDatabase.updateRecords(movie);
                    if (rs > 0) {
                        MsgDialog.displayInformationDialog("Information", movieName + " change showtime to " + newShowtime);
                        reset();
                    } else
                        MsgDialog.displayErrorDialog("Error", "Error while updating " + movieName);
                } else
                    throw new MyException("You need to delete all the tickets sold in this movie first");
            } else
                throw new MyException("Invalid input");
        } catch (Exception e) {
            e.printStackTrace();
            reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void exit() {
        stage.close();
    }

    public void reset() {
        newShowtime.setText("");
        chooseMovie.setValue("");
        currentShowtime.setValue("");
    }
}
