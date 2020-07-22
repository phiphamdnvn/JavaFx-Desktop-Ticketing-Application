package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Movie;

import java.util.List;

public class ViewMovieController2 {
    @FXML
    TableView table;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> timeSlotCol;
    @FXML
    private TableColumn<?, ?> languageCol;
    @FXML
    private TableColumn<?, ?> subtitleCol;
    @FXML
    private TableColumn<?, ?> classificationCol;
    Stage stage = null;

    public void loadData(Stage stage) throws Exception {
        this.stage = stage;
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));
        timeSlotCol.setCellValueFactory(new PropertyValueFactory("timeSlot"));
        languageCol.setCellValueFactory(new PropertyValueFactory("language"));
        subtitleCol.setCellValueFactory(new PropertyValueFactory("subtitle"));
        classificationCol.setCellValueFactory(new PropertyValueFactory("classification"));
        try {
            List<Movie> movies = AccessingMovieDatabase.retrieveAllRecords();
            for (int i = 0; i < movies.size(); i++) {
                table.getItems().add(movies.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
