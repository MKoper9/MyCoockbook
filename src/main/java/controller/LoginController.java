package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Recipe;
import service.LoginService;
import service.WindowService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_register;
    @FXML
    private LoginService loginService;
    @FXML
    private Label lbl_error;
    @FXML
    private Scene scene;
    private WindowService windowService;




    public void initialize() {
        loginService = new LoginService();
        windowService = new WindowService();

    }



    public void loginAction(ActionEvent actionEvent) {
        if(loginService.login(lbl_error, tf_login, pf_password)==true) {
            windowService.openNewWindow("/view/cookbookview.fxml", "Książka Kucharska");
            windowService.closeCurrentWindow(lbl_error);
        }
    }

    public void keyloginaction(KeyEvent keyEvent) {
        if ((keyEvent.getCode() == KeyCode.ENTER)) {
            if(loginService.login(lbl_error, tf_login, pf_password)==true) {
                windowService.openNewWindow("/view/cookbookview.fxml", "Książka Kucharska");
                windowService.closeCurrentWindow(lbl_error);
            }
        }
    }

    public void entermouseactionreg(MouseEvent mouseEvent) {
        loginService.setButtonColor(btn_register, "#66cc33");
    }

    public void entermouseaction(MouseEvent mouseEvent) {
        loginService.setButtonColor(btn_login, "#66cc33");
    }

    public void exitmouseaction(MouseEvent mouseEvent) {
        loginService.setButtonColor(btn_login, "#3366ff");
    }

    public void exitmouseactionreg(MouseEvent mouseEvent) {
        loginService.setButtonColor(btn_register, "#3366ff");
    }

    public void registerAction(ActionEvent actionEvent) {

        //wywołaj metodę tworzącą nowe okno
        windowService.openNewWindow("/view/register.fxml","Panel Rejestracji");
        windowService.closeCurrentWindow(lbl_error);
    }

}
