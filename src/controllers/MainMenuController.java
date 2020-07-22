package controllers;

import gui.MovieMaintenance;
import gui.SeatMaintenance;
import gui.TicketMaintenance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainMenuController {
    Stage stage;
    @FXML
    FXMLLoader loader;


    public void start(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void movieMaintenance() {
        try {
            String file = "../gui/MovieMaintenance.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            MovieMaintenance mv = new MovieMaintenance();
            mv.getStarted(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seatMaintenance() throws Exception {
        SeatMaintenance st = new SeatMaintenance();
        String file = "../gui/SeatMaintenance.fxml";
        loader = new FXMLLoader(this.getClass().getResource(file));
        stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        st.getStarted(stage);
        stage.show();
    }

    public void ticketMaintenance() throws Exception {
        String file = "../gui/TicketMaintenance.fxml";
        loader = new FXMLLoader(this.getClass().getResource(file));
        stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        TicketMaintenance tk = new TicketMaintenance();
        tk.getStarted(stage);
        stage.show();
    }
}
