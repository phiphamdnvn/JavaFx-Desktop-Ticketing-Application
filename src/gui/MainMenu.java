package gui;

import controllers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainMenu {
    MainMenuController ctrl;
    String mainMenuFile;
    FXMLLoader loader;
    Stage stage;

    public MainMenu(Stage stage) throws Exception {
        ctrl = new MainMenuController();
        mainMenuFile = "MainMenu.fxml";
        loader = new FXMLLoader(this.getClass().getResource(mainMenuFile));
        loader.setController(ctrl);
        if(stage == null){
            stage = new Stage();
        }
        this.stage = stage;
        ctrl.start(this.stage);
        this.stage.setScene(new Scene(loader.load()));
        this.stage.show();
    }

}
