package gui;

import controllers.UserLoginController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class UserLogin {
    Stage stage;

    public void getStarted() throws Exception {
        UserLoginController ctrl = new UserLoginController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UserLogin.fxml"));
        loader.setController(ctrl);
        //stage = new Stage();
        ctrl.getStarted(stage);
        //ctrl.getMedia();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void playVideo(Stage stage) {
        this.stage = stage;
        String path = "D:\\Phi Pham\\IntelliJIDEA Workspace\\CSCP2014 Assignment 2\\src\\gui\\Video.mp4";

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class
        MediaView mediaView = new MediaView(mediaPlayer);

        //by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);

        //setting group and scene
        Group root = new Group();
        root.getChildren().add(mediaView);
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            getStarted();
                            mediaPlayer.stop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("P2 Cinema");
        stage.setResizable(false);
        stage.show();
    }
}
