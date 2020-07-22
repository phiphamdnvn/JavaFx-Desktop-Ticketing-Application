package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import models.Movie;

import java.util.List;

public class DeleteMovieController {
    @FXML
    ComboBox chooseMovie;
    Stage stage;
    Movie movie;

    public void populateValue(Stage stage) throws Exception {
        this.stage = stage;
        List<Movie> movies = AccessingMovieDatabase.retrieveAllRecords();
        for (int i = 0; i < movies.size(); i++) {
            chooseMovie.getItems().add(movies.get(i).getName());
        }
    }

    public void delete() throws Exception {
        try {
            String movieName;
            movieName = chooseMovie.getSelectionModel().getSelectedItem().toString();
            movie = new Movie();
            movie.setName(movieName);
            if (!AccessingTicketDatabase.isExistMovie(movie)) {
                int rs = AccessingMovieDatabase.deleteRecords(movie);
                if (rs > 0) {
                    MsgDialog.displayInformationDialog("Information", movieName + "deleted successfully");
                    stage.close();
                } else
                    MsgDialog.displayErrorDialog("Error", "Error while deleting " + movieName);
            } else
                throw new MyException("You need to delete all the tickets sold in this movie first");
        } catch (Exception e) {
            e.printStackTrace();
            chooseMovie.setValue("");
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed\n" + e.toString());
        }
    }

    public void exit() {
        stage.close();
    }
}
