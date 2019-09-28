package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class WindowService {
    public void openNewWindow(String windowPath, String title){
        Stage registerStage = new Stage();
        Parent window = null;
        try {
            window = FXMLLoader.load(getClass().getResource(windowPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(window);
        scene.getStylesheets().add("css/init.css");
        registerStage.setTitle(title);

        registerStage.setScene(scene);

        registerStage.show();
    }

    public void closeCurrentWindow(Node node){
        //dla podanego node - kontrolki pobierz stage w którym znajduje się znajduja
        Stage currentStage = (Stage)node.getScene().getWindow();
        currentStage.close();

    }

    public Optional<ButtonType> getConfrimationAlert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public void getAlert(Alert.AlertType alertType, String title, String header, String content)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
