package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ContentsController {

    @FXML
    public void goAncient(ActionEvent event) {
        try {
            System.out.println("Ancient clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/ancient-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.err.println("Error loading Ancient: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goMedieval(ActionEvent event) {
        try {
            System.out.println("Medieval clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/medieval-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.err.println("Error loading Medieval: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goRenaissance(ActionEvent event) {
        try {
            System.out.println("Renaissance clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/renaissance-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.err.println("Error loading Renaissance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goIndustrial(ActionEvent event) {
        try {
            System.out.println("Industrial clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/industrial-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.err.println("Error loading Industrial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void go20thCent(ActionEvent event) {
        try {
            System.out.println("20th Century clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/century20th-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.err.println("Error loading 20th Century: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goModern(ActionEvent event) {
        try {
            System.out.println("Modern clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/modern-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.err.println("Error loading Modern: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goToPantry(ActionEvent event) {
        try {
            System.out.println("Pantry clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/pantry-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e){
            System.out.println("Error loading Pantry Matcher: " + e.getMessage());
            e.printStackTrace();
        }
    }

    }
