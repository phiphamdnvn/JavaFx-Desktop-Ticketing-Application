package controllers;

import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class DeleteSeatController {
    Stage stage;
    Image img;
    @FXML
    ImageView imageView;

    public boolean getStarted(Stage stage) throws Exception {
        this.stage = stage;
        return AccessingSeatDatabase.isExistSeat();
    }

    public boolean getStarted2(Stage stage) throws Exception {
        this.stage = stage;
        boolean isExistSeat = false;
        for (int i = 0; i < 12; i++) {
            if (AccessingTicketDatabase.isExistSeat(Character.toString(0 + 69) + (i + 1))) {
                isExistSeat = true;
                break;
            }
        }
        return isExistSeat;
    }

    public void getImage() throws Exception {
        FileInputStream file = new FileInputStream("D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\image\\FullSeat.jpg");
        img = new Image(file);
        imageView.setImage(img);
    }

    public void getImage2() throws Exception {
        FileInputStream file = new FileInputStream("D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\image\\FixedSeat.jpg");
        img = new Image(file);
        imageView.setImage(img);
    }

    public void delete() throws Exception {
        int rs = AccessingSeatDatabase.deleteExtendedSeat();
        if (rs > 0) {
            String file = "../gui/DeleteSeat2.fxml";
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(file));
            DeleteSeatController ctrl = new DeleteSeatController();
            loader.setController(ctrl);
            stage.setScene(new Scene(loader.load()));
            ctrl.getStarted(stage);
            ctrl.getImage2();
            stage.show();
        } else
            MsgDialog.displayErrorDialog("Error", "Error occur while deleting extended seat");
    }

    public void exit() {
        stage.close();
    }
}
