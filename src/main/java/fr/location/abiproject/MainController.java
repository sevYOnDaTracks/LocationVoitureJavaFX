package fr.location.abiproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button seDeconnecter ;

    @FXML
    void SeDeconnecter(ActionEvent event) {
        // Rediriger vers la page d'inscription
        Stage stage = (Stage) seDeconnecter.getScene().getWindow();
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuil.fxml"));
        try {
            // Charger la page d'inscription
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
