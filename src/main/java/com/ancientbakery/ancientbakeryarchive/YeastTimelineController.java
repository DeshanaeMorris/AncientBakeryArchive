package com.ancientbakery.ancientbakeryarchive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class YeastTimelineController extends BaseNavigator {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @FXML private LineChart<String, Number> yeastTimelineChart;
    @FXML private CategoryAxis eraAxis;
    @FXML private NumberAxis countAxis;

    @FXML
    public void initialize() {
        loadYeastTimelineChart();
    }

    private void loadYeastTimelineChart() {
        if (yeastTimelineChart == null) {
            return;
        }

        yeastTimelineChart.getData().clear();
        yeastTimelineChart.setAnimated(false);
        yeastTimelineChart.setCreateSymbols(true);
        yeastTimelineChart.setLegendVisible(true);

        Map<String, Map<String, Integer>> timelineData = recipeRepository.getYeastTimelineData();
        String[] agentTypes = {"Sourdough starter", "Yeast", "Chemical leavening (baking powder/soda)", "Instant yeast"};
        for (String agentType : agentTypes) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(agentType);

            for (Map.Entry<String, Map<String, Integer>> eraEntry : timelineData.entrySet()) {
                int count = eraEntry.getValue().getOrDefault(agentType, 0);
                series.getData().add(new XYChart.Data<>(eraEntry.getKey(), count));
            }

            yeastTimelineChart.getData().add(series);
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        goTo(event, "analytics-hub-view.fxml");
    }
}
