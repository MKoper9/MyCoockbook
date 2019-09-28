package service;

import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import utility.InMemoryDB;

import java.util.Optional;


public class LoginService {

public boolean login(Label lbl_error, TextField tf_login, PasswordField pf_passwword)
{
    Optional <User>loggedUser = InMemoryDB.users.stream()
            .filter(
            user -> user.getLogin().equals(tf_login.getText())
            &&user.getPassword().equals(pf_passwword.getText()))
            .findAny();

    if(loggedUser.isPresent())
    {
        lbl_error.setText("Zalogowano");
        lbl_error.setStyle("-fx-text-fill: green");
        lbl_error.setVisible(true);
        return true;
    }
    else
    {
        lbl_error.setText("Błędne dane");
        lbl_error.setStyle("-fx-text-fill: red");
        lbl_error.setVisible(true);
        return false;
    }
}


public void setButtonColor(Button button, String color)
{
    button.setStyle("-fx-background-color: "+color);
}

}
