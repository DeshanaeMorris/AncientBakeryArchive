package com.ancientbakery.ancientbakeryarchive;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class StartController {

    @FXML
    private Button welcomeButton;

    @FXML
    private void onWelcomeClick() {
        try {
            // Table of Contents page
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("contents-view.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) welcomeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Ancient Bakery Archive — Table of Contents");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading contents page: " + e.getMessage());
        }
    }
}
