package com.ancientbakery.ancientbakeryarchive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ModernController {
    @FXML
    public void goAncient(ActionEvent event) {
        try {
            System.out.println("Ancient clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ancient-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Ancient: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goMedieval(ActionEvent event) {
        try {
            System.out.println("Medieval clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("medieval-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Medieval: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    public void goRenaissance(ActionEvent event) {
        try {
            System.out.println("Renaissance clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("renaissance-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Renaissance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goIndustrial(ActionEvent event) {
        try {
            System.out.println("Industrial clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("industrial-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Industrial: " + e.getMessage());
            e.printStackTrace();
        }    }

    @FXML
    public void go20thCent(ActionEvent event) {
        try {
            System.out.println("20th Century clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("century20th-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading 20th Century: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goModern(ActionEvent event) {
        System.out.println("Modern clicked!");
    }

    @FXML
    public void goBakeNow(ActionEvent event) {
        try {
            System.out.println("Bake Now clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bakenow-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Bake Now: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
