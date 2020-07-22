package gui;

import controllers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieMaintenance {
    ViewMovieController ctrl1;
    String file;
    FXMLLoader loader;
    Stage stage;
    @FXML
    private Button exit;
    @FXML
    private Button view;

    public void getStarted(Stage stage) {
        this.stage = stage;
    }

    public void viewMovie() throws Exception {
        try {
            ctrl1 = new ViewMovieController();
            file = "ViewMovie.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            loader.setController(ctrl1);
            stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            ctrl1.loadData(stage);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void addMovie() throws Exception {
        try {
            file = "AddMovie.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            AddMovieController ctrl = new AddMovieController();
            loader.setController(ctrl);
            stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ctrl.populateValues(stage);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void updateMovie() throws Exception {
        try {
            file = "UpdateMovie.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            UpdateMovieController ctrl = new UpdateMovieController();
            loader.setController(ctrl);
            stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ctrl.populateValues(stage);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed\n" + e.toString());
        }
    }

    public void deleteMovie() throws Exception {
        try {
            file = "DeleteMovie.fxml";
            String file2 = "ViewMovie2.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            FXMLLoader loader2 = new FXMLLoader(this.getClass().getResource(file2));
            DeleteMovieController ctrl = new DeleteMovieController();
            ViewMovieController2 ctrl2 = new ViewMovieController2();
            loader.setController(ctrl);
            loader2.setController(ctrl2);

            AnchorPane ac = new AnchorPane();
            ac.getChildren().add(loader2.load());
            ac.getChildren().add(loader.load());
            stage = new Stage();
            stage.setScene(new Scene(ac, 832, 562));
            ctrl2.loadData(stage);
            ctrl.populateValue(stage);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed\n" + e.toString());
        }
    }
}
