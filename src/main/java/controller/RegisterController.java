package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import service.LoginService;
import service.RegisterService;
import service.WindowService;
import utility.InMemoryDB;

import java.util.Optional;

public class RegisterController {


    private WindowService windowService;
    private RegisterService registerService;


    public void initialize() {
        windowService = new WindowService();
        registerService = new RegisterService();

    }

    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField pf_password;

    @FXML
    private PasswordField pf_password_confirm;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_register;

    @FXML
    private Label lbl_error;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_password_confirm;

    @FXML
    private CheckBox cb_showpassword;


    @FXML
    void backAction(ActionEvent event) {

        windowService.openNewWindow("/view/loginView.fxml", "Panel logowania");
        windowService.closeCurrentWindow(lbl_error);

    }

    @FXML
    void entermouseaction(MouseEvent event) {

    }

    @FXML
    void entermouseactionreg(MouseEvent event) {

    }

    @FXML
    void exitmouseaction(MouseEvent event) {

    }

    @FXML
    void exitmouseactionreg(MouseEvent event) {

    }

    @FXML
    void keyregisteraction(KeyEvent event) {

    }

    @FXML
    void registerAction(ActionEvent event) {
        // sprawdzenie czy pola są niepuste gdy cb jest zaznaczony
        if (cb_showpassword.isSelected() && (registerService.fieldIsEmpty(tf_login.getText(), lbl_error) ||
                registerService.fieldIsEmpty(tf_password.getText(), lbl_error) ||
                registerService.fieldIsEmpty(tf_password_confirm.getText(), lbl_error))) {
            System.out.println("puste1");
            // sprawdzenie czy pola są niepuste gdy cb nie jest zaznaczony
        } else if (!cb_showpassword.isSelected() && (registerService.fieldIsEmpty(tf_login.getText(), lbl_error) ||
                registerService.fieldIsEmpty(pf_password.getText(), lbl_error) ||
                registerService.fieldIsEmpty(pf_password_confirm.getText(), lbl_error))) {
            System.out.println("puste2");
            // gdy pola nie są puste
        } else {
            if (registerService.loginCheck(tf_login, lbl_error)) {
                if (registerService.passwordsEquality(
                        cb_showpassword, tf_password, pf_password, tf_password_confirm, pf_password_confirm, lbl_error)) {
                    System.out.println("rejestrujemy");
                    Optional<ButtonType> confirm = windowService
                            .getConfrimationAlert("Potwierdzenie rejestracji",
                                    "Potwierdź dane rejestracji\nlogin: " + tf_login.getText() + "\nhasło: " + pf_password.getText());
                    if (confirm.get() == ButtonType.OK) {
                        registerService.saveUser(tf_login, cb_showpassword, tf_password, pf_password);
                        lbl_error.setVisible(true);
                        lbl_error.setText("ZAREJESTROWANO UŻYTKOWNIKA " + tf_login.getText().toUpperCase());
                        registerService.clearField(tf_login, tf_password, tf_password_confirm, pf_password, pf_password_confirm);
                        lbl_error.setStyle("-fx-text-fill: blue");
                    } else {
                        lbl_error.setVisible(true);
                        lbl_error.setText("REJESTRACJA ODRZUCONA");
                        lbl_error.setStyle("-fx-text-fill: red");
                    }
                }
            }
        }
    }
    @FXML
    void showPassword(ActionEvent event) {
        {
            registerService.showPassword(cb_showpassword, tf_password, pf_password, tf_password_confirm, pf_password_confirm);
        }
    }


}
