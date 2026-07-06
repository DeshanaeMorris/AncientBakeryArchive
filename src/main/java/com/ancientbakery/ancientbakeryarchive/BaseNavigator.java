package com.ancientbakery.ancientbakeryarchive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseNavigator {
    private static final double DEFAULT_WIDTH = 800;
    private static final double DEFAULT_HEIGHT = 600;

    public static void setResponsiveScene(Stage stage, Parent root) {
        Scene currentScene = stage.getScene();
        double width = currentScene != null && currentScene.getWidth() > 0
                ? currentScene.getWidth()
                : DEFAULT_WIDTH;
        double height = currentScene != null && currentScene.getHeight() > 0
                ? currentScene.getHeight()
                : DEFAULT_HEIGHT;

        boolean wasMaximized = stage.isMaximized();
        boolean wasFullScreen = stage.isFullScreen();

        stage.setScene(new Scene(root, width, height));
        stage.setMaximized(wasMaximized);
        stage.setFullScreen(wasFullScreen);
        stage.show();
    }

    protected void goTo(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/ancientbakery/ancientbakeryarchive/fxml/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.err.println("Error loading " + fxmlFile + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goAncient(ActionEvent event) {
        goTo(event, "ancient-view.fxml");
    }

    public void goMedieval(ActionEvent event) {
        goTo(event, "medieval-view.fxml");
    }

    public void goRenaissance(ActionEvent event) {
        goTo(event, "renaissance-view.fxml");
    }

    public void goIndustrial(ActionEvent event) {
        goTo(event, "industrial-view.fxml");
    }

    public void go20thCent(ActionEvent event) {
        goTo(event, "century20th-view.fxml");
    }

    public void goModern(ActionEvent event) {
        goTo(event, "modern-view.fxml");
    }

    public void goBakeNow(ActionEvent event) {
        goTo(event, "bakenow-view.fxml");
    }

    public void goAnalytics(ActionEvent event) {
        goTo(event, "analytics-hub-standalone-view.fxml");
    }

    @FXML
    public void goToContents(ActionEvent event) {
        goTo(event, "contents-view.fxml");
    }
}
