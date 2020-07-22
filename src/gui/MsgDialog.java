package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MsgDialog {
	public static void displayInformationDialog(String title, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(msg);	
		alert.show();
	}
	
	public static void displayErrorDialog(String title, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		alert.show();
	}
}