package gui;

import controllers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicketMaintenance {
    String file;
    Stage stage;
    @FXML
    FXMLLoader loader;

    public void getStarted(Stage stage) {
        this.stage = stage;
    }

    public void addTicket() throws Exception {
        try {
            file = "AddTicket.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            AddTicketController ctrl = new AddTicketController();
            loader.setController(ctrl);
            stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ctrl.start(stage);
            stage.setResizable(false);
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

    public void viewTicket() throws Exception {
        try {
            file = "ViewTicket.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            ViewTicketController ctrl = new ViewTicketController();
            loader.setController(ctrl);
            stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ctrl.getStared(stage);
            ctrl.populateValue();
            stage.setResizable(false);
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

    public void deleteTicket() throws Exception {
        try {
            file = "DeleteTicket.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            DeleteTicketController ctrl = new DeleteTicketController();
            loader.setController(ctrl);
            stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ctrl.populateValue(stage);
            stage.setResizable(false);
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

    public void updateTicket() throws Exception {
        try {
            file = "UpdateTicket.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            UpdateTicketController ctrl = new UpdateTicketController();
            loader.setController(ctrl);
            stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ctrl.getStarted(stage);
            ctrl.populateValue();
            stage.setResizable(false);
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

}
