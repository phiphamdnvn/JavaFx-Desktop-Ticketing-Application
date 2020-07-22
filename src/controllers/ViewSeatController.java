package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Seat;

import java.util.List;

public class ViewSeatController {
    @FXML
    TableView table;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> vipSeatCol;
    @FXML
    private TableColumn<?, ?> seatNumberCol;
    @FXML
    private TableColumn<?, ?> seatRowCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    Stage stage;

    public void loadData(Stage stage) throws Exception {
        this.stage = stage;
        typeCol.setCellValueFactory(new PropertyValueFactory("status"));
        vipSeatCol.setCellValueFactory(new PropertyValueFactory("vipSeat"));
        seatNumberCol.setCellValueFactory(new PropertyValueFactory("seatNumber"));
        seatRowCol.setCellValueFactory(new PropertyValueFactory("seatRow"));
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        List<Seat> seats = AccessingSeatDatabase.retrieveAllRecords();
        for (int i = 0; i < seats.size(); i++) {
            table.getItems().add(seats.get(i));
        }
    }

    public void exit() {
        stage.close();
    }
}
