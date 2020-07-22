package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Seat;

import java.io.FileInputStream;
import java.util.List;


public class UpdateSeatController {
    Seat seat;
    Stage stage;
    @FXML
    ComboBox row, number;
    @FXML
    TextField vipSeat;
    @FXML
    RadioButton yes, no;
    @FXML
    ImageView imageView;


    public void getStared(Stage stage) {
        this.stage = stage;
    }

    public void getSeatInfo(Seat seat) {
        this.seat = seat;
    }

    public void getImage() throws Exception {
        List<Seat> seats = AccessingSeatDatabase.retrieveAllRecords();
        FileInputStream file1 = new FileInputStream("D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\image\\FixedSeat.jpg");
        FileInputStream file2 = new FileInputStream("D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\image\\FullSeat.jpg");
        Image img1 = new Image(file1);
        Image img2 = new Image(file2);
        if (seats.size() == 48) {
            imageView.setImage(img1);
        } else
            imageView.setImage(img2);
    }

    public void populateValues(Stage stage) throws Exception {
        this.stage = stage;
        for (int i = 0; i < 5; i++) {
            if (i == 4 & !AccessingSeatDatabase.isExistSeat())
                break;
            row.getItems().add(Character.toString(i + 65));
            if (i > 0)
                continue;
            for (int j = 0; j < 12; j++)
                number.getItems().add(j + 1);
        }
    }

    public void populateValues2(Seat seat) throws Exception {
        vipSeat.setText(seat.getVipSeat());
    }

    public void goNext() throws Exception {
        try {
            String seatRow = row.getSelectionModel().getSelectedItem().toString();
            String seatNumber = number.getSelectionModel().getSelectedItem().toString();
            if (AccessingTicketDatabase.isExistSeat(seatRow + seatNumber))
                throw new MyException("This seat are being occupied by customer, please check the ticket module");
            seat = new Seat();
            seat.setSeatRow(seatRow);
            seat.setSeatNumber(Integer.parseInt(seatNumber));
            AccessingSeatDatabase.retrieveOneRecord(seat);

            String file = "../gui/UpdateSeat2.fxml";
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(file));
            UpdateSeatController ctrl = new UpdateSeatController();
            loader.setController(ctrl);
            stage.setScene(new Scene(loader.load()));
            ctrl.getStared(stage);
            ctrl.populateValues2(seat);
            ctrl.getSeatInfo(this.seat);
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
            String vipSeat = "";
            if (yes.isSelected())
                vipSeat = "yes";
            else
                vipSeat = "no";
            if (!vipSeat.equalsIgnoreCase(seat.getVipSeat())) {
                seat.setVipSeat(vipSeat);
                seat.setPrice();
                int rs = AccessingSeatDatabase.updateSeat(seat);
                if (rs > 0) {
                    MsgDialog.displayInformationDialog("Information", "Seat " + seat.getSeatRow() + seat.getSeatNumber() + " are updated");
                    stage.close();
                } else
                    MsgDialog.displayErrorDialog("Error", "Error while updating " + "Seat " + seat.getSeatRow() + seat.getSeatNumber());
            } else
                throw new MyException("Action not allowed");
        } catch (Exception e) {
            e.printStackTrace();
            //reset();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void reset() {

    }

    public void exit() {
        stage.close();
    }
}
