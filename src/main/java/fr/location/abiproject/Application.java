package fr.location.abiproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
        primaryStage.setTitle("Location Voiture - Anani Abigaël");
        primaryStage.setScene(new Scene(root, 1090, 736));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}






