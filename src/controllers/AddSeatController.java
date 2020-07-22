package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Seat;

import java.util.ArrayList;
import java.util.List;

public class AddSeatController {
    @FXML
    ComboBox type, seatNumber, seatRow;
    @FXML

    RadioButton E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12;
    Stage stage;
    Seat seat;

    public void start(Stage stage) {
        this.stage = stage;
    }

    public void populateValues(Stage stage) {
        this.stage = stage;
        type.getItems().add("Single");
        type.getItems().add("Couple");
        for (int i = 1; i <= 24; i++) {
            seatNumber.getItems().add(i);
            if (i > 4)
                continue;
            seatRow.getItems().add((char) (i + 64));
        }
    }

    public boolean getInfo() throws Exception {
        return AccessingSeatDatabase.isExistSeat();
    }

    public void goNext() throws Exception {
        String file = "../gui/AddSeat.fxml";
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(file));
        AddSeatController ctrl = new AddSeatController();
        loader.setController(ctrl);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        ctrl.start(stage);
        stage.show();
    }

    public void add() throws Exception {
        int i = 0;
        List<Integer> rs = new ArrayList<Integer>();
        List<RadioButton> E = new ArrayList<>();
        E.add(E1);
        E.add(E2);
        E.add(E3);
        E.add(E4);
        E.add(E5);
        E.add(E6);
        E.add(E7);
        E.add(E8);
        E.add(E9);
        E.add(E10);
        E.add(E11);
        E.add(E12);

        for (i = 0; i < 12; i++) {
            seat = new Seat();
            seat.setSeatRow("E");
            seat.setSeatNumber(i + 1);
            if (E.get(i).isSelected())
                seat.setVipSeat("yes");
            else
                seat.setVipSeat("no");
            seat.setStatus();
            seat.setPrice();
            rs.add(AccessingSeatDatabase.insertNewRecords(seat));
            if (rs.get(i) == 0)
                break;
        }
        if (rs.size() == 12) {
            MsgDialog.displayInformationDialog("Information", "Row E added successfully");
            stage.close();
        } else {
            MsgDialog.displayErrorDialog("Error", "Error occur while adding the seat" + rs.indexOf(i));
            AccessingSeatDatabase.deleteExtendedSeat();
        }
    }

    public void exit() {
        stage.close();
    }
}
