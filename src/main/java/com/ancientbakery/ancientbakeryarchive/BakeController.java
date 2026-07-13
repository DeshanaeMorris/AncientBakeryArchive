package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.features.RecipeScaler;
import com.ancientbakery.ancientbakeryarchive.features.UnitConverter;
import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import com.ancientbakery.ancientbakeryarchive.model.RecipeIngredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BakeController extends BaseNavigator {
    private static final String ORIGINAL_UNITS = "Original Units";
    private static final String IMPERIAL_UNITS = "Imperial";
    private static final String METRIC_UNITS = "Metric";

    @FXML private Label selectedRecipeLabel;
    @FXML private Label statusLabel;
    @FXML private TextField multiplierField;
    @FXML private ComboBox<String> unitSystemBox;
    @FXML private TextArea historicalRecipeText;
    @FXML private TextArea modernRecipeText;

    private final RecipeRepository recipeRepository = new RecipeRepository();
    private Recipe selectedRecipe;
    private List<RecipeIngredient> selectedIngredients = List.of();

    @FXML
    public void initialize() {
        multiplierField.setText("1");
        unitSystemBox.getItems().setAll(ORIGINAL_UNITS, IMPERIAL_UNITS, METRIC_UNITS);
        unitSystemBox.setValue(ORIGINAL_UNITS);
        unitSystemBox.setOnAction(event -> refreshRecipeDisplays());

        loadSelectedRecipe();
    }

    @FXML
    private void updateModernRecipe() {
        refreshRecipeDisplays();
    }

    private void loadSelectedRecipe() {
        if (!AppState.hasSelectedRecipe()) {
            selectedRecipeLabel.setText("No recipe selected");
            statusLabel.setText("Select a recipe from an era page, then click BAKE NOW.");
            historicalRecipeText.setText("No historical recipe selected yet.");
            modernRecipeText.setText("No modernized recipe selected yet.");
            return;
        }

        selectedRecipe = recipeRepository.findRecipeById(AppState.getSelectedRecipeId());

        if (selectedRecipe == null) {
            selectedRecipeLabel.setText("Recipe not found");
            statusLabel.setText("The selected recipe could not be loaded from the database.");
            historicalRecipeText.setText("Recipe data unavailable.");
            modernRecipeText.setText("Recipe data unavailable.");
            return;
        }

        selectedIngredients = recipeRepository.findIngredientsByRecipeId(selectedRecipe.getId());
        selectedRecipeLabel.setText(selectedRecipe.getName());
        refreshRecipeDisplays();
    }

    private void refreshRecipeDisplays() {
        if (selectedRecipe == null) {
            return;
        }

        try {
            double multiplier = parsePositiveNumber(multiplierField.getText());
            String unitSystem = getSelectedUnitSystem();

            historicalRecipeText.setText(buildHistoricalRecipeText());
            modernRecipeText.setText(buildModernRecipeText(multiplier, unitSystem));
            statusLabel.setText("");
        } catch (IllegalArgumentException e) {
            statusLabel.setText(e.getMessage());
        }
    }

    private String buildHistoricalRecipeText() {
        return "Original Recipe\n" +
                "-----------------\n" +
                selectedRecipe.getName() + "\n\n" +
                "Source: " + safeText(selectedRecipe.getSource(), "Not listed") + "\n" +
                "Base portion: " + selectedRecipe.getBasePortion() + "\n\n" +
                "Original temperature: " + safeText(selectedRecipe.getTemperatureDescription(), "Not listed") + "\n\n" +
                "Ingredients:\n" + formatOriginalIngredients() + "\n\n" +
                "Original Recipe Text:\n" + safeText(selectedRecipe.getOriginalText(), "Not available");
    }

    private String buildModernRecipeText(double multiplier, String unitSystem) {
        boolean useImperial = IMPERIAL_UNITS.equals(unitSystem);

        return "Modernized Recipe\n" +
                "-----------------\n" +
                selectedRecipe.getName() + "\n\n" +
                "Portion multiplier: " + format(multiplier) + "\n\n" +
                "Units: " + unitSystem + "\n\n" +
                "Modernized temperature: " + formatModernTemperature(selectedRecipe.getTemperatureDescription(), useImperial) + "\n\n" +
                "Modernized Ingredients:\n" + formatModernIngredients(multiplier, unitSystem) + "\n\n" +
                "Modernized Instructions:\n" + formatModernizedInstructions(selectedRecipe.getModernizedText(), useImperial);
    }

    private String getSelectedUnitSystem() {
        if (unitSystemBox == null || unitSystemBox.getValue() == null) {
            return ORIGINAL_UNITS;
        }
        return unitSystemBox.getValue();
    }

    private String formatOriginalIngredients() {
        if (selectedIngredients.isEmpty()) {
            return "No ingredients listed.";
        }

        return selectedIngredients.stream()
                .map(ingredient -> "• " + ingredient.getIngredientName() + " — " +
                        format(ingredient.getQuantity()) + " " + safeText(ingredient.getUnit(), ""))
                .collect(Collectors.joining("\n"));
    }

    private String formatModernIngredients(double multiplier, String unitSystem) {
        if (selectedIngredients == null || selectedIngredients.isEmpty()) {
            return "No ingredients listed.";
        }

        return selectedIngredients.stream()
                .map(ingredient -> {
                    double amount = RecipeScaler.scaleAmount(ingredient.getQuantity(), multiplier);

                    if (IMPERIAL_UNITS.equals(unitSystem)) {
                        UnitConverter.ConvertedMeasurement converted =
                                UnitConverter.toImperial(amount, ingredient.getUnit());

                        return "• " + ingredient.getIngredientName() + " — " +
                                format(converted.amount()) + " " + converted.unit();
                    }

                    if (METRIC_UNITS.equals(unitSystem)) {
                        UnitConverter.ConvertedMeasurement converted =
                                UnitConverter.toMetric(amount, ingredient.getUnit());

                        return "• " + ingredient.getIngredientName() + " — " +
                                format(converted.amount()) + " " + converted.unit();
                    }

                    return "• " + ingredient.getIngredientName() + " — " +
                            format(amount) + " " + safeText(ingredient.getUnit(), "");
                })
                .collect(Collectors.joining("\n"));
    }

    private String formatModernizedInstructions(String instructions, boolean useImperial) {
        String instructionText = safeText(instructions, "Not available");
        String convertedInstructions = containsBothTemperatureUnits(instructionText)
                ? instructionText
                : convertTemperaturesInText(instructionText, useImperial);

        if (!hasExplicitTemperature(convertedInstructions)) {
            String thermalNote = inferModernTemperature(useImperial);

            if (!thermalNote.equals("Not listed")) {
                convertedInstructions += "\n\nThermal conversion note: " + thermalNote;
            }
        }

        return convertedInstructions;
    }

    private String formatModernTemperature(String originalTemperature, boolean useImperial) {
        String temperatureText = safeText(originalTemperature, "Not listed");

        if (containsBothTemperatureUnits(temperatureText)) {
            return temperatureText;
        }

        if (hasExplicitTemperature(temperatureText)) {
            return convertTemperaturesInText(temperatureText, useImperial);
        }

        return inferModernTemperature(useImperial);
    }

    private String inferModernTemperature(boolean useImperial) {
        String original = safeText(selectedRecipe == null ? null : selectedRecipe.getTemperatureDescription(), "");
        String modernized = safeText(selectedRecipe == null ? null : selectedRecipe.getModernizedText(), "");
        String combined = (original + " " + modernized).toLowerCase(Locale.ROOT);

        String modernizedTemperature = findFirstConvertedTemperature(modernized, useImperial);
        if (modernizedTemperature != null) {
            return "Interpreted as " + modernizedTemperature + " based on the modernized recipe instructions.";
        }

        if (containsAny(combined, "no baking", "refrigerate", "chill until set", "chill", "let cool")) {
            return "No oven temperature needed — chill or let set as directed.";
        }

        if (containsAny(combined, "stovetop", "simmer", "boil", "boiled", "saucepan", "medium heat", "medium-low flame", "shallow frying pan")) {
            return "No oven temperature needed — use stovetop heat as directed.";
        }

        if (containsAny(combined, "fry", "frying", "hot oil", "skillet")) {
            return "No oven temperature needed — fry over medium to medium-high heat until done.";
        }

        if (containsAny(combined, "broil", "torch", "glaze")) {
            return formatThermalEstimate(500, useImperial) + " broiler or direct high heat, briefly.";
        }

        if (containsAny(combined, "wood-fired", "wood fired", "hearth", "clay pot", "under a pot", "under a hot clay pot")) {
            return formatThermalEstimate(375, useImperial) + " moderate-hot oven; use a covered Dutch oven or oven-safe pot if needed.";
        }

        if (containsAny(combined, "little heat", "gentle heat", "slowly", "slow oven")) {
            return formatThermalEstimate(325, useImperial) + " low-to-moderate oven.";
        }

        if (containsAny(combined, "hot oven", "very hot", "strong heat")) {
            return formatThermalEstimate(400, useImperial) + " hot oven.";
        }

        if (containsAny(combined, "bake", "oven")) {
            return formatThermalEstimate(350, useImperial) + " moderate oven.";
        }

        return "Not listed";
    }

    private String findFirstConvertedTemperature(String text, boolean useImperial) {
        Pattern pattern = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*°?\\s*([FC])\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(safeText(text, ""));

        if (!matcher.find()) {
            return null;
        }

        double value = Double.parseDouble(matcher.group(1));
        String unit = matcher.group(2).toUpperCase(Locale.ROOT);
        double fahrenheit = unit.equals("F") ? value : (value * 9 / 5) + 32;

        return formatThermalEstimate(fahrenheit, useImperial);
    }

    private String convertTemperaturesInText(String text, boolean useImperial) {
        Pattern pattern = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*°?\\s*([FC])\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(1));
            String unit = matcher.group(2).toUpperCase(Locale.ROOT);

            double fahrenheit = unit.equals("F") ? value : (value * 9 / 5) + 32;
            double celsius = unit.equals("C") ? value : (value - 32) * 5 / 9;

            String replacement = useImperial
                    ? format(fahrenheit) + "°F (" + format(celsius) + "°C)"
                    : format(celsius) + "°C (" + format(fahrenheit) + "°F)";

            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(result);
        return result.toString();
    }

    private boolean hasExplicitTemperature(String text) {
        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?\\s*°?\\s*[FC]\\b", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(safeText(text, "")).find();
    }

    private boolean containsBothTemperatureUnits(String text) {
        String safe = safeText(text, "");
        Pattern celsiusPattern = Pattern.compile("\\d+(?:\\.\\d+)?\\s*°?\\s*C\\b", Pattern.CASE_INSENSITIVE);
        Pattern fahrenheitPattern = Pattern.compile("\\d+(?:\\.\\d+)?\\s*°?\\s*F\\b", Pattern.CASE_INSENSITIVE);
        return celsiusPattern.matcher(safe).find() && fahrenheitPattern.matcher(safe).find();
    }

    private String formatThermalEstimate(double fahrenheit, boolean useImperial) {
        double celsius = (fahrenheit - 32) * 5 / 9;

        if (useImperial) {
            return format(fahrenheit) + "°F (" + format(celsius) + "°C)";
        }

        return format(celsius) + "°C (" + format(fahrenheit) + "°F)";
    }

    private boolean containsAny(String text, String... keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private double parsePositiveNumber(String input) {
        try {
            double value = Double.parseDouble(input.trim());

            if (value <= 0) {
                throw new IllegalArgumentException("Please enter a positive portion multiplier.");
            }

            return value;
        } catch (Exception e) {
            throw new IllegalArgumentException("Please enter a valid number for the portion multiplier.");
        }
    }

    private String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((int) value);
        }
        return String.format("%.2f", value);
    }

    private String safeText(String value) {
        return value == null ? "" : value;
    }

    private String safeText(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    @FXML
    public void goToContents(ActionEvent event) {
        goTo(event, "contents-view.fxml");
    }
}
