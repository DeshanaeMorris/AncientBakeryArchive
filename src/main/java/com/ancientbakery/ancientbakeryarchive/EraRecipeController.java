package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Era;
import com.ancientbakery.ancientbakeryarchive.model.Glossary;
import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import com.ancientbakery.ancientbakeryarchive.model.RecipeIngredient;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EraRecipeController extends BaseNavigator {
    @FXML
    private VBox eraTitleBox;

    @FXML
    private VBox recipeCardsBox;

    private static List<Glossary> glossaryTerms;

    private final RecipeRepository recipeRepository = new RecipeRepository();
    private VBox selectedCard;

    protected abstract int getEraId();

    @FXML
    public void initialize() {
        loadRecipeTab();
    }

    private void loadRecipeTab() {
        Era era = recipeRepository.findEraById(getEraId());
        loadEraTitle(era);
        loadRecipeCards();
    }

    private void loadEraTitle(Era era) {
        if (eraTitleBox == null) {
            return;
        }

        eraTitleBox.getChildren().clear();

        String eraName = era == null ? "Historical Era" : safeText(era.getName(), "Historical Era");
        String timePeriod = era == null ? "" : safeText(era.getTimePeriod(), "");

        Label title = new Label(eraName + " Recipes");
        title.getStyleClass().add("era-intro-title");
        title.setWrapText(true);
        title.setMaxWidth(480);

        Label dates = new Label(timePeriod);
        dates.getStyleClass().add("era-intro-dates");
        dates.setWrapText(true);
        dates.setMaxWidth(480);
        dates.setVisible(!timePeriod.isBlank());
        dates.setManaged(!timePeriod.isBlank());

        eraTitleBox.getChildren().addAll(title, dates);
    }

    private void loadRecipeCards() {
        if (recipeCardsBox == null) {
            return;
        }

        recipeCardsBox.getChildren().clear();
        List<Recipe> recipes = recipeRepository.findRecipesByEra(getEraId());

        if (recipes.isEmpty()) {
            Label emptyMessage = new Label("No recipes have been added for this era yet.");
            emptyMessage.getStyleClass().add("recipe-card");
            emptyMessage.setPadding(new Insets(16));
            emptyMessage.setWrapText(true);
            recipeCardsBox.getChildren().add(emptyMessage);
            return;
        }

        VBox firstCard = null;
        Recipe firstRecipe = null;

        for (Recipe recipe : recipes) {
            VBox card = createRecipeCard(recipe);
            if (firstCard == null) {
                firstCard = card;
                firstRecipe = recipe;
            }
            recipeCardsBox.getChildren().add(card);
        }

        if (firstCard != null && firstRecipe != null) {
            selectRecipe(firstRecipe, firstCard);
        }
    }

    private List<Glossary> getGlossaryTerms() {
        if (glossaryTerms == null) {
            glossaryTerms = recipeRepository.findAllGlossaryTerms();
        }
        return glossaryTerms;
    }

    private VBox createRecipeCard(Recipe recipe) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(14));
        card.getStyleClass().add("recipe-card");
        card.setPrefWidth(560);
        card.setMaxWidth(760);

        Label title = new Label(safeText(recipe.getName(), "Untitled Recipe"));
        title.getStyleClass().add("recipe-card-title");
        title.setWrapText(true);
        title.setMaxWidth(720);

        Label historicalHeader = new Label("Original Text:");
        historicalHeader.getStyleClass().add("recipe-meta");

        TextFlow historicalText = buildOriginalTextFlow(recipe.getOriginalText());

        Label ingredientsHeader = new Label("Ingredients:");
        ingredientsHeader.getStyleClass().add("recipe-meta");

        Label ingredients = new Label(formatIngredients(recipe.getId()));
        ingredients.getStyleClass().add("recipe-body");
        ingredients.setWrapText(true);
        ingredients.setMaxWidth(720);

        card.setOnMouseClicked(event -> selectRecipe(recipe, card));
        card.getChildren().addAll(title, historicalHeader, historicalText, ingredientsHeader, ingredients);
        return card;
    }

    private TextFlow buildOriginalTextFlow(String text) {
        TextFlow flow = new TextFlow();
        flow.getStyleClass().add("recipe-body");
        flow.setMaxWidth(720);

        List<Glossary> terms = getGlossaryTerms();

        if (text == null || text.isBlank() || terms.isEmpty()) {
            flow.getChildren().add(new Text(safeText(text, "Not available")));
            return flow;
        }

        List<Glossary> sorted = new ArrayList<>(terms);
        sorted.sort((a, b) -> b.getTerm().length() - a.getTerm().length());

        String pattern = sorted.stream()
                .map(g -> "\\b" + Pattern.quote(g.getTerm()) + "\\b")
                .collect(Collectors.joining("|"));

        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(text);
        int lastEnd = 0;

        while (matcher.find()) {
            if (matcher.start() > lastEnd) {
                flow.getChildren().add(new Text(text.substring(lastEnd, matcher.start())));
            }

            String matched = matcher.group();
            Glossary g = sorted.stream()
                    .filter(t -> t.getTerm().equalsIgnoreCase(matched))
                    .findFirst()
                    .orElse(null);

            Text run = new Text(matched);
            if (g != null) {
                run.getStyleClass().add("old-term");
                Tooltip tooltip = new Tooltip(g.getDefinition() + "\nModern: " + g.getModernSubstitute());
                tooltip.setShowDelay(Duration.millis(100));
                Tooltip.install(run, tooltip);
            }
            flow.getChildren().add(run);
            lastEnd = matcher.end();
        }

        if (lastEnd < text.length()) {
            flow.getChildren().add(new Text(text.substring(lastEnd)));
        }

        return flow;
    }

    private String formatIngredients(int recipeId) {
        List<RecipeIngredient> ingredients = recipeRepository.findIngredientsByRecipeId(recipeId);

        if (ingredients.isEmpty()) {
            return "No ingredients listed.";
        }

        return ingredients.stream()
                .map(ingredient -> "• " + safeText(ingredient.getIngredientName(), "Ingredient") +
                        " — " + format(ingredient.getQuantity()) + " " + safeText(ingredient.getUnit(), ""))
                .collect(Collectors.joining("\n"));
    }

    private void selectRecipe(Recipe recipe, VBox card) {
        if (selectedCard != null) {
            selectedCard.getStyleClass().remove("recipe-card-selected");
        }

        selectedCard = card;
        if (!card.getStyleClass().contains("recipe-card-selected")) {
            card.getStyleClass().add("recipe-card-selected");
        }

        AppState.selectRecipe(recipe.getId());
    }

    private String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((int) value);
        }
        return String.format("%.2f", value);
    }

    private String safeText(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }
}
