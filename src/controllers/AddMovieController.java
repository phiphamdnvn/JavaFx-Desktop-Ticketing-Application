package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Movie;

public class AddMovieController {
    @FXML
    TextField id, name, showtime, language, type;
    @FXML
    ComboBox classification;
    @FXML
    RadioButton chinese, bahasa;
    Stage stage;
    Movie movie;

    public void populateValues(Stage stage) {
        this.stage = stage;
        classification.getItems().add("18+");
        classification.getItems().add("13+");
        classification.getItems().add("None");
        classification.setValue("");
    }

    public void add() throws Exception {
        try {
            int movieId;
            String movieName = "", movieType = "", movieShowtime = "", movieLanguage = "", movieSubtitle = "", movieClassification = "";
            movieId = Integer.parseInt(id.getText());
            movieName = name.getText();
            movieShowtime = showtime.getText();
            movieLanguage = language.getText();
            movieType = type.getText();
            if (chinese.isSelected())
                movieSubtitle = "Chinese";
            else
                movieSubtitle = "Bahasa Malay";
            movieClassification = classification.getSelectionModel().getSelectedItem().toString();
            if (Validation.isValidId(Integer.toString(movieId)) & Validation.isValidName(movieName) & Validation.isValidType(movieType) & Validation.isValidShowTime(movieShowtime) & Validation.isValidType(movieLanguage)) {
                if (movieShowtime.equalsIgnoreCase("24:00"))
                    movieShowtime = "00:00";
                movie = new Movie(movieId, movieName, movieType, movieShowtime, movieLanguage, movieSubtitle, movieClassification);
            } else {
                throw new MyException("Invalid input");
            }
            if (AccessingMovieDatabase.isExistMovie(movie) == 0) {
                int rs = AccessingMovieDatabase.insertNewRecords(movie);
                if (rs > 0) {
                    MsgDialog.displayInformationDialog("Information", movieName + " inserted successfully");
                    reset();
                } else
                    MsgDialog.displayErrorDialog("Error", "Error while inserting " + movieName);
            }else
                throw new MyException("Existed Movie! Please refer to view all movie");
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

    public void reset() {
        classification.setValue("");
        chinese.setSelected(false);
        bahasa.setSelected(false);
        id.setText("");
        name.setText("");
        type.setText("");
        showtime.setText("");
        language.setText("");
    }
}
