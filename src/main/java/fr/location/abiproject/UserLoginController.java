package fr.location.abiproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.io.IOException;

public class UserLoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button retourButton;

    @FXML
    private Text errorMessage;

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticateUser(username, password)) {

            // Rediriger l'utilisateur vers la page d'accueil


        } else {
            showAlert("Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Paramètres de connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/abi";
        String user = "root";
        String pass = "root";

        // Requête pour sélectionner l'enregistrement du client avec le nom d'utilisateur fourni
        String query = "SELECT * FROM customer WHERE user_id = ?";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Vérifier si le mot de passe fourni correspond à l'enregistrement du client
                String passwordFromDB = rs.getString("password");
                if (passwordFromDB.equals(password)) {
                    showAlert("Connexion reussie !");
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Les informations d'authentification sont incorrectes
        return false;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void reveniralacceuil(ActionEvent event) {
        // Rediriger vers la page d'inscription
        Stage stage = (Stage) retourButton.getScene().getWindow();
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
