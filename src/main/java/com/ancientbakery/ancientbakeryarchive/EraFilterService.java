package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EraFilterService {

    public List<Recipe> getRecipesByEra(int eraId) {
        List<Recipe> recipes = new ArrayList<>();

        String sql = "SELECT * FROM Recipes WHERE era_id = ?";

        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, eraId);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Recipe recipe = makeRecipeFromResults(results);
                recipes.add(recipe);
            }

            results.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error filtering recipes by era: " + e.getMessage());
        }

        return recipes;
    }

    public List<Recipe> searchRecipesByWord(String word) {
        List<Recipe> recipes = new ArrayList<>();

        String sql = "SELECT * FROM Recipes " +
                "WHERE name LIKE ? " +
                "OR original_text LIKE ? " +
                "OR modernized_text LIKE ?";

        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            String searchWord = "%" + word + "%";

            statement.setString(1, searchWord);
            statement.setString(2, searchWord);
            statement.setString(3, searchWord);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Recipe recipe = makeRecipeFromResults(results);
                recipes.add(recipe);
            }

            results.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error searching recipes by word: " + e.getMessage());
        }

        return recipes;
    }

    public List<Recipe> getRecipesByIngredient(String ingredientName) {
        List<Recipe> recipes = new ArrayList<>();

        String sql = "SELECT DISTINCT r.* " +
                "FROM Recipes r " +
                "JOIN Recipe_Ingredients ri ON r.id = ri.Recipes_ID " +
                "JOIN Ingredients i ON ri.Ingredients_ID = i.id " +
                "WHERE i.name LIKE ?";

        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + ingredientName + "%");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Recipe recipe = makeRecipeFromResults(results);
                recipes.add(recipe);
            }

            results.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error searching recipes by ingredient: " + e.getMessage());
        }

        return recipes;
    }

    private Recipe makeRecipeFromResults(ResultSet results) throws Exception {
        Recipe recipe = new Recipe();

        recipe.setId(results.getInt("id"));
        recipe.setName(results.getString("name"));
        recipe.setEraId(results.getInt("era_id"));
        recipe.setOriginalText(results.getString("original_text"));
        recipe.setModernizedText(results.getString("modernized_text"));
        recipe.setTemperatureDescription(results.getString("temperature_description"));
        recipe.setBasePortion(results.getInt("base_portion"));
        recipe.setSource(results.getString("sources"));
        recipe.setImageUrl(results.getString("image_url"));

        return recipe;
    }
}