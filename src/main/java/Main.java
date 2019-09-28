import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //ściągam zasób z pliku fxml
        Parent window = FXMLLoader.load(getClass().getResource("view/loginView.fxml"));
        //ustawiam scenę
        Scene scene = new Scene(window);
        scene.getStylesheets().add("css/init.css");
        //nazwa sceny/okienka
        primaryStage.setTitle("Panel logowania");
        //ustawia zasoby z edytora(pliku fxml) na okienku z całymi ustawieniami
        primaryStage.setScene(scene);
        //zatrzymuje okno na ekranie
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
