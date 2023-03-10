package fr.location.abiproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;



    @FXML
    void handleSignup(ActionEvent event) {
        // Rediriger vers la page d'inscription
        Stage stage = (Stage) signupButton.getScene().getWindow();
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("user_registration.fxml"));
        try {
            // Charger la page d'inscription
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLogin(ActionEvent event) {
        // Rediriger vers la page d'inscription
        Stage stage = (Stage) loginButton.getScene().getWindow();
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("user_login.fxml"));
        try {
            // Charger la page d'inscription
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

