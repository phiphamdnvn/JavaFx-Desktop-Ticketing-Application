package gui;

import controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeatMaintenance {
    String file;
    FXMLLoader loader;
    Stage stage;

    public void getStarted(Stage stage) {
        this.stage = stage;
    }

    public void addSeat() throws Exception {
        try {
            file = "AddSeat2.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            AddSeatController ctrl = new AddSeatController();
            loader.setController(ctrl);
            stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            ctrl.start(stage);
            if (ctrl.getInfo()) {
                MsgDialog.displayInformationDialog("Information", "All extended seat are added in");
                stage.close();
            } else {
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void viewSeat() throws Exception {
        ViewSeatController ctrl = new ViewSeatController();
        file = "ViewSeat.fxml";
        loader = new FXMLLoader(this.getClass().getResource(file));
        loader.setController(ctrl);
        stage = new Stage();
        stage.setResizable(false);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        ctrl.loadData(stage);
        stage.show();
    }

    public void deleteSeat() throws Exception {
        try {
            DeleteSeatController ctrl = new DeleteSeatController();
            file = "DeleteSeat.fxml";
            loader = new FXMLLoader(this.getClass().getResource(file));
            loader.setController(ctrl);
            stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            if (!ctrl.getStarted(stage)) {
                throw new MyException("There are no extended seat");
            } else if (ctrl.getStarted2(stage)) {
                throw new MyException("Row E are being used to serve customer(s), please refer to ticket module");
            } else {
                ctrl.getImage();
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MyException)
                MsgDialog.displayErrorDialog("Error", e.toString());
            else
                MsgDialog.displayErrorDialog("Error", "Action not allowed \n" + e.toString());
        }
    }

    public void updateSeat() throws Exception {
        UpdateSeatController ctrl = new UpdateSeatController();
        file = "UpdateSeat.fxml";
        loader = new FXMLLoader(this.getClass().getResource(file));
        loader.setController(ctrl);
        stage = new Stage();
        stage.setResizable(false);
        this.stage.setScene(new Scene(loader.load()));
        ctrl.populateValues(stage);
        ctrl.getImage();
        stage.show();
    }
}
