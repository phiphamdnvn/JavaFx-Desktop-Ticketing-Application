package controllers;

import gui.MainMenu;
import gui.MsgDialog;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;

public class UserLoginController {
    Stage stage;
    MainMenu mainMenu;
    @FXML
    TextField userName, password;
    @FXML
    MediaView mediaView;

    public void getStarted(Stage stage) {
        this.stage = stage;
    }

    public void getMedia() {
        String file = "D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\gui\\Video.mp4";
        URL resource = getClass().getResource("../gui/Video.mp4");
        //Media media = new Media(new File(file).toURI().toString());
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void accessApp() throws Exception {
        String userName = this.userName.getText();
        String password = this.password.getText();
        if (userName.equalsIgnoreCase("phipham") & password.equalsIgnoreCase("1234")) {
            mainMenu = new MainMenu(stage);
        } else
            MsgDialog.displayErrorDialog("Error", "You are not authorized user");
    }

    public void exit() throws Exception {
        stage.close();
    }


}
