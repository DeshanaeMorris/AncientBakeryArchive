package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.features.RecipeScaler;
import com.ancientbakery.ancientbakeryarchive.features.UnitConverter;
import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import com.ancientbakery.ancientbakeryarchive.model.RecipeIngredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BakeController extends BaseNavigator {

    private static final String ORIGINAL_UNITS = "Original Units";
    private static final String IMPERIAL_UNITS = "Imperial";
    private static final String METRIC_UNITS = "Metric";

    @FXML
    private Label selectedRecipeLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField multiplierField;

    @FXML
    private ComboBox<String> unitSystemBox;

    @FXML
    private TextArea historicalRecipeText;

    @FXML
    private TextArea modernRecipeText;

    @FXML
    private ImageView featuredRecipeImage;

    @FXML
    private Label featuredRecipeLabel;

    @FXML
    private Tab featuredPicturesTab;

    @FXML
    private ImageView historyPlaceholderIcon;

    private final RecipeRepository recipeRepository = new RecipeRepository();

    private Recipe selectedRecipe;
    private Recipe featuredRecipe;

    private List<RecipeIngredient> selectedIngredients = List.of();

    @FXML
    public void initialize() {
        multiplierField.setText("1");

        unitSystemBox.getItems().setAll(
                ORIGINAL_UNITS,
                IMPERIAL_UNITS,
                METRIC_UNITS
        );

        unitSystemBox.setValue(ORIGINAL_UNITS);
        unitSystemBox.setOnAction(event -> refreshRecipeDisplays());

        loadSelectedRecipe();
        loadRandomFeaturedRecipe();
        loadHistoryPlaceholderImage();

        featuredPicturesTab.setOnSelectionChanged(event -> {
            if (featuredPicturesTab.isSelected()) {
                loadRandomFeaturedRecipe();
            }
        });
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

    private void loadRandomFeaturedRecipe() {
        List<Recipe> recipes = recipeRepository.findAllRecipes();

        if (recipes.isEmpty()) {
            featuredRecipeLabel.setText("No featured recipe available.");
            featuredRecipeImage.setImage(null);
            return;
        }

        Random random = new Random();
        Recipe newFeaturedRecipe;

        if (recipes.size() == 1) {
            newFeaturedRecipe = recipes.get(0);
        } else {
            do {
                int randomIndex = random.nextInt(recipes.size());
                newFeaturedRecipe = recipes.get(randomIndex);
            } while (featuredRecipe != null
                    && newFeaturedRecipe.getId() == featuredRecipe.getId());
        }

        featuredRecipe = newFeaturedRecipe;

        featuredRecipeLabel.setText(featuredRecipe.getName());
        loadFeaturedRecipeImage();
    }

    private void loadFeaturedRecipeImage() {
        String imageName = featuredRecipe.getImageUrl();

        if (imageName == null || imageName.isBlank()) {
            featuredRecipeImage.setImage(null);
            System.out.println("No image listed for the featured recipe.");
            return;
        }

        String imagePath =
                "/com/ancientbakery/ancientbakeryarchive/images/" + imageName;

        var imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            featuredRecipeImage.setImage(null);
            System.out.println("Could not find image: " + imagePath);
            return;
        }

        Image image = new Image(imageStream);
        featuredRecipeImage.setImage(image);
    }

    private void loadHistoryPlaceholderImage() {
        String imagePath =
                "/com/ancientbakery/ancientbakeryarchive/images/parchment_scroll3.png";

        var imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            historyPlaceholderIcon.setImage(null);
            System.out.println("Could not find image: " + imagePath);
            return;
        }

        Image image = new Image(imageStream);
        historyPlaceholderIcon.setImage(image);
    }

    @FXML
    private void openFeaturedRecipe(MouseEvent event) {
        if (featuredRecipe == null) {
            return;
        }

        AppState.selectRecipeFromBakeNowPictures(featuredRecipe.getId());

        String eraFile = getEraFile(featuredRecipe.getEraId());

        if (eraFile == null) {
            System.out.println(
                    "Could not find an era screen for era ID: "
                            + featuredRecipe.getEraId()
            );
            return;
        }

        openEraScreen(event, eraFile);
    }

    private String getEraFile(int eraId) {
        if (eraId == 1) {
            return "ancient-view.fxml";
        } else if (eraId == 2) {
            return "medieval-view.fxml";
        } else if (eraId == 3) {
            return "renaissance-view.fxml";
        } else if (eraId == 4) {
            return "industrial-view.fxml";
        } else if (eraId == 5) {
            return "century20th-view.fxml";
        } else if (eraId == 6) {
            return "modern-view.fxml";
        }

        return null;
    }

    private void openEraScreen(MouseEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/ancientbakery/ancientbakeryarchive/fxml/"
                                    + fxmlFile
                    )
            );

            Parent root = loader.load();

            Node clickedItem = (Node) event.getSource();
            Stage stage = (Stage) clickedItem.getScene().getWindow();

            BaseNavigator.setResponsiveScene(stage, root);

        } catch (IOException e) {
            System.out.println("Could not open " + fxmlFile);
            e.printStackTrace();
        }
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
        return convertTemperaturesInText(safeText(instructions, "Not available"), useImperial);
    }

    private String formatModernTemperature(String originalTemperature, boolean useImperial) {
        String temperatureText = safeText(originalTemperature, "Not listed");
        return convertTemperaturesInText(temperatureText, useImperial);
    }

    private String convertTemperaturesInText(String text, boolean useImperial) {
        Pattern pattern = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*°?([FC])\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(1));
            String unit = matcher.group(2).toUpperCase();

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

    private String safeText(String value, String fallback) {
        if (value == null || value.isBlank()) {
            return fallback;
        }

        return value;
    }

    @FXML
    private void goToContents(ActionEvent event) {
        goTo(event, "contents-view.fxml");
    }
}
