package com.ancientbakery.ancientbakeryarchive;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SweetenerGraphController extends BaseNavigator {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @FXML private LineChart<String, Number> sweetenerChart;
    @FXML private CategoryAxis eraAxis;
    @FXML private NumberAxis countAxis;

    @FXML
    public void initialize() {
        List<Map<String, Object>> rows = recipeRepository.getSweetenerUsageByEra();

        Map<String, XYChart.Series<String, Number>> seriesBySweetener = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {
            String era = (String) row.get("era_name");
            String sweetener = (String) row.get("sweetener_name");
            int count = (Integer) row.get("usage_count");

            XYChart.Series<String, Number> series = seriesBySweetener.get(sweetener);
            if (series == null) {
                series = new XYChart.Series<>();
                series.setName(sweetener);
                seriesBySweetener.put(sweetener, series);
            }

            series.getData().add(new XYChart.Data<>(era, count));
        }

        sweetenerChart.getData().addAll(seriesBySweetener.values());

        Platform.runLater(this::wireLegendClicks);
    }

    private void wireLegendClicks() {
        for (Node legendItem : sweetenerChart.lookupAll(".chart-legend-item")) {
            if (legendItem instanceof Label legendLabel) {
                String sweetenerName = legendLabel.getText();
                legendLabel.setOnMouseClicked(event -> handleLegendClick(sweetenerName));
                legendLabel.setStyle(legendLabel.getStyle() + "-fx-cursor: hand;");
            }
        }
    }

    private boolean comparisonActive = false;
    private String activeComparison = null;

    private void handleLegendClick(String clickedSweetener) {
        if (clickedSweetener.equalsIgnoreCase("Honey")) {
            resetAllSeriesOpacity();
            comparisonActive = false;
            activeComparison = null;
            return;
        }

        if (comparisonActive && clickedSweetener.equals(activeComparison)) {
            resetAllSeriesOpacity();
            comparisonActive = false;
            activeComparison = null;
            return;
        }

        for (XYChart.Series<String, Number> series : sweetenerChart.getData()) {
            boolean shouldHighlight = series.getName().equalsIgnoreCase("Honey")
                    || series.getName().equals(clickedSweetener);
            series.getNode().setOpacity(shouldHighlight ? 1.0 : 0.15);
        }

        comparisonActive = true;
        activeComparison = clickedSweetener;
    }

    private void resetAllSeriesOpacity() {
        for (XYChart.Series<String, Number> series : sweetenerChart.getData()) {
            series.getNode().setOpacity(1.0);
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        goTo(event, "analytics-hub-standalone-view.fxml");
    }
}
