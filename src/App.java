import gui.MsgDialog;
import gui.UserLogin;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primaryStage) throws Exception {
        UserLogin user = new UserLogin();
        user.playVideo(primaryStage);
    }

    public static void main(String[] args) {
        try {
            Application.launch();
        } catch (Exception e) {
            e.printStackTrace();
            MsgDialog.displayErrorDialog("Error", "Action not allowed");
        }
    }
}
