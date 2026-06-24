package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Era;
import com.ancientbakery.ancientbakeryarchive.model.Glossary;
import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import com.ancientbakery.ancientbakeryarchive.model.RecipeIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    public Era findEraById(int eraId) {
        String sql = "SELECT id, name, time_period, History_text FROM Eras WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eraId);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Era era = new Era();
                    era.setId(result.getInt("id"));
                    era.setName(result.getString("name"));
                    era.setTimePeriod(result.getString("time_period"));
                    era.setHistoryText(result.getString("History_text"));
                    return era;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Recipe findRecipeById(int recipeId) {
        String sql = "SELECT * FROM Recipes WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recipeId);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapRecipe(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Recipe> findRecipesByEra(int eraId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipes WHERE era_id = ? ORDER BY id";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eraId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    recipes.add(mapRecipe(result));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    public List<RecipeIngredient> findIngredientsByRecipeId(int recipeId) {
        List<RecipeIngredient> ingredients = new ArrayList<>();
        String sql = """
                SELECT ri.id,
                       ri.Recipes_ID,
                       ri.Ingredients_ID,
                       ri.Quantity,
                       ri.Unit,
                       i.name AS ingredient_name
                FROM Recipe_Ingredients ri
                JOIN Ingredients i ON i.id = ri.Ingredients_ID
                WHERE ri.Recipes_ID = ?
                ORDER BY ri.id
                """;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recipeId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    RecipeIngredient ingredient = new RecipeIngredient();
                    ingredient.setId(result.getInt("id"));
                    ingredient.setRecipeId(result.getInt("Recipes_ID"));
                    ingredient.setIngredientId(result.getInt("Ingredients_ID"));
                    ingredient.setQuantity(result.getDouble("Quantity"));
                    ingredient.setUnit(result.getString("Unit"));
                    ingredient.setIngredientName(result.getString("ingredient_name"));
                    ingredients.add(ingredient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    private Recipe mapRecipe(ResultSet result) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(result.getInt("id"));
        recipe.setName(result.getString("name"));
        recipe.setEraId(result.getInt("era_id"));
        recipe.setOriginalText(result.getString("original_text"));
        recipe.setModernizedText(result.getString("modernized_text"));
        recipe.setTemperatureDescription(result.getString("temperature_description"));
        recipe.setBasePortion(result.getInt("base_portion"));
        recipe.setSource(result.getString("Sources"));
        recipe.setImageUrl(result.getString("image_url"));
        return recipe;
    }

    public List<Glossary> findAllGlossaryTerms() {
        List<Glossary> terms = new ArrayList<>();
        String sql = "SELECT id, Term, Definition, Modern_Substitute FROM Glossary";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Glossary term = new Glossary();
                    term.setId(result.getInt("id"));
                    term.setTerm(result.getString("Term"));
                    term.setDefinition(result.getString("Definition"));
                    term.setModernSubstitute(result.getString("Modern_Substitute"));
                    terms.add(term);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return terms;
    }
}
