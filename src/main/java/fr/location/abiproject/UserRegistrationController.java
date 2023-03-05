package fr.location.abiproject;


import com.gluonhq.charm.glisten.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserRegistrationController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField addressField;

    @FXML
    private DatePicker dobPicker;


    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button acceuilButton;

    private Connection conn;

    public void initialize() {
        // initialisation de la connexion à la base de données
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abi", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerUser(ActionEvent event) {
        // récupération des données du formulaire
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String contact = contactField.getText();
        String address = addressField.getText();
        LocalDate dob = dobPicker.getValue();
        String userId = userIdField.getText();
        String password = passwordField.getText();

        // validation des données
        if (firstName.isEmpty() || lastName.isEmpty() || contact.isEmpty() || address.isEmpty() || dob == null
                 || userId.isEmpty() || password.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return;
        }

        Pattern contactPattern = Pattern.compile("^\\d{10}$");
        Matcher contactMatcher = contactPattern.matcher(contact);
        if (!contactMatcher.matches()) {
            showAlert("Le numéro de contact doit contenir 10 chiffres.");
            return;
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dobStr = dob.format(formatter);

        // insertion des données dans la base de données
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO customer "
                    + "(first_name, last_name, contact, address_cust, date_of_birth, password, user_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, contact);
            stmt.setString(4, address);
            stmt.setString(5, dobStr);
            stmt.setString(6, password);
            stmt.setString(7, userId);
            stmt.executeUpdate();
            showAlert("L'inscription a été effectuée avec succès! Vous pouvez vous connecter ...");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Une erreur est survenue lors de l'inscription. Veuillez réessayer.Il est possible que l'identifiant que vous avez choisi soit déja utilisé.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void revenirALAcceuil(ActionEvent event) {
        // Rediriger vers la page de connexion
        Stage stage = (Stage) acceuilButton.getScene().getWindow();
        // Charger le fichier FXML de la page de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuil.fxml"));
        try {
            // Charger la page de connexion
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

