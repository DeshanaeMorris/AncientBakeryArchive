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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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
    private TextField searchField;

    @FXML
    private ListView<String> searchResultsList;

    private final Map<String, Integer> searchResultMap = new LinkedHashMap<>();

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

        searchResultsList.setOnMouseClicked(event -> {
            String selected = searchResultsList.getSelectionModel().getSelectedItem();
            if (selected == null || !searchResultMap.containsKey(selected)) return;

            int recipeId = searchResultMap.get(selected);
            AppState.selectRecipe(recipeId);
            navigateToRecipeEra(recipeId, event);
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
        if (value == null || value.isBlank()) {
            return fallback;
        }

        return value;
    }

    @FXML
    public void handleSearch(ActionEvent event) {
        String query = searchField.getText().trim().toLowerCase();

        if (query.isEmpty()) {
            searchResultsList.setVisible(false);
            searchResultsList.setManaged(false);
            return;
        }

        searchResultMap.clear();
        searchResultsList.getItems().clear();

        String sql = """
        SELECT DISTINCT r.id, r.name, e.name AS era_name
        FROM Recipes r
        JOIN Eras e ON r.era_id = e.id
        WHERE LOWER(r.name) LIKE ?
           OR LOWER(e.name) LIKE ?
           OR r.id IN (
               SELECT ri.Recipes_ID
               FROM Recipe_Ingredients ri
               JOIN Ingredients i ON i.id = ri.Ingredients_ID
               WHERE LOWER(i.name) LIKE ?
           )
        ORDER BY e.name, r.name
        """;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String pattern = "%" + query + "%";
            statement.setString(1, pattern);
            statement.setString(2, pattern);
            statement.setString(3, pattern);

            try (ResultSet result = statement.executeQuery()) {
                int rowCount = 0;
                while (result.next()) {
                    rowCount++;
                    String display = result.getString("name") + "  —  " + result.getString("era_name");
                    searchResultMap.put(display, result.getInt("id"));
                    searchResultsList.getItems().add(display);
                    System.out.println("Added to list: " + display);
                }
                System.out.println("Rows found: " + rowCount);
                System.out.println("ListView items size: " + searchResultsList.getItems().size());
            }

            searchResultsList.setVisible(true);
            searchResultsList.setManaged(true);

            boolean hasResults = !searchResultsList.getItems().isEmpty();
            searchResultsList.setVisible(hasResults);
            searchResultsList.setManaged(hasResults);

            int itemCount = searchResultsList.getItems().size();
            double rowHeight = 34;
            double calculatedHeight = Math.min(itemCount * rowHeight + 2, 220);
            searchResultsList.setPrefHeight(calculatedHeight);

            if (!hasResults) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Matches");
                alert.setHeaderText(null);
                alert.setContentText("No recipes, eras, or ingredients matched \"" + query + "\". ");
                alert.setGraphic(null);

                alert.getDialogPane().setStyle("-fx-background-color: #f7f1e3; -fx-font-family: 'Georgia';");

                Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
                if (contentLabel != null) {
                    contentLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-text-fill: #2b1d12;");
                }

                alert.getDialogPane().getButtonTypes().forEach(type -> {
                    var button = alert.getDialogPane().lookupButton(type);
                    button.setStyle("-fx-background-color: #271d15; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-background-radius: 6;");
                });

                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Handles the screen transition when clicking a search result in the sidebar
    private void navigateToRecipeEra(int recipeId, MouseEvent event) {
        RecipeRepository repo = new RecipeRepository();
        Recipe recipe = repo.findRecipeById(recipeId);

        if (recipe == null) return;

        String fxmlFile = switch (recipe.getEraId()) {
            case 1 -> "ancient-view.fxml";
            case 2 -> "medieval-view.fxml";
            case 3 -> "renaissance-view.fxml";
            case 4 -> "industrial-view.fxml";
            case 5 -> "century20th-view.fxml";
            case 6 -> "modern-view.fxml";
            default -> "contents-view.fxml";
        };

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/ancientbakery/ancientbakeryarchive/fxml/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.out.println("Error navigating to recipe era: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goToContents(ActionEvent event) {
        goTo(event, "contents-view.fxml");
    }
}
