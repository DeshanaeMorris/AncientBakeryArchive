package com.ancientbakery.ancientbakeryarchive;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EraBreakdownController extends BaseNavigator {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @FXML private PieChart breakdownChart;
    @FXML private ComboBox<String> eraSelector;

    @FXML
    public void initialize() {
        // Populate the era dropdown in chronological order
        eraSelector.setItems(FXCollections.observableArrayList(
                "Ancient", "Medieval", "Renaissance",
                "Industrial", "20th Century", "Modern"
        ));

        // Load Ancient by default so the chart isn't blank on arrival
        eraSelector.setValue("Ancient");
        loadChart("Ancient");
    }

    @FXML
    public void onEraSelected(ActionEvent event) {
        String selectedEra = eraSelector.getValue();
        if (selectedEra != null) {
            loadChart(selectedEra);
        }
    }

    private void loadChart(String eraName) {
        Map<String, Integer> breakdown = recipeRepository.getIngredientCategoryBreakdown(eraName);

        List<PieChart.Data> slices = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : breakdown.entrySet()) {
            slices.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        breakdownChart.getData().clear();
        Platform.runLater(() -> {
            breakdownChart.setData(FXCollections.observableArrayList(slices));
            breakdownChart.setTitle(eraName + " — Ingredient Category Breakdown");
            breakdownChart.setLabelsVisible(true);
            breakdownChart.setLegendVisible(true);
        });
    }

    @FXML
    public void goBack(ActionEvent event) {
        goTo(event, "analytics-hub-standalone-view.fxml");
    }
}