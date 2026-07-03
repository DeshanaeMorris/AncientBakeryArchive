package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Era;
import com.ancientbakery.ancientbakeryarchive.model.Glossary;
import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import com.ancientbakery.ancientbakeryarchive.model.RecipeIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    public String getHistoryByEraId(int eraId) {
        String sql = "SELECT history_text FROM Eras WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, eraId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("history_text");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No historical context available for this era.";
    }
    public Map<String, Integer> getCookingFatRankings() {
        Map<String, Integer> rankings = new LinkedHashMap<>();
        String[] fats = {"Butter", "Oil", "Lard", "Suet", "Fat"};

        try (Connection conn = DatabaseManager.getConnection()) {
            for (String fat : fats) {
                String sql = """
                        SELECT COUNT(DISTINCT ri.Recipes_ID) 
                        FROM Recipe_Ingredients ri
                        JOIN Ingredients i ON i.id = ri.Ingredients_ID
                        WHERE LOWER(i.name) LIKE ?
                        """;
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, "%" + fat.toLowerCase() + "%");
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            rankings.put(fat, rs.getInt(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error calculating fat rankings: " + e.getMessage());
            e.printStackTrace();
        }
        return rankings;
    }


    public Map<String, Map<String, Integer>> getYeastTimelineData() {
        Map<String, Map<String, Integer>> timeline = new LinkedHashMap<>();
        String[] agentTypes = {"Sourdough starter", "Yeast", "Chemical leavening (baking powder/soda)", "Instant yeast"};
        String eraSql = "SELECT id, name FROM Eras ORDER BY id";

        String agentSql = """
                SELECT e.name AS era_name,
                       CASE
                           WHEN LOWER(i.name) LIKE '%instant yeast%' THEN 'Instant yeast'
                           WHEN LOWER(i.name) LIKE '%sourdough%' OR LOWER(i.name) LIKE '%starter%' THEN 'Sourdough starter'                           
                           WHEN LOWER(i.name) LIKE '%baking powder%'
                           OR LOWER(i.name) LIKE '%baking soda%'
                           OR LOWER(i.name) LIKE '%cream of tartar%' THEN 'Chemical leavening (baking powder/soda)'
                           WHEN LOWER(i.name) LIKE '%yeast%' THEN 'Yeast'
                           ELSE 'Other'
                       END AS agent_type,
                       COUNT(DISTINCT r.id) AS recipe_count
                FROM Eras e
                JOIN Recipes r ON r.era_id = e.id
                JOIN Recipe_Ingredients ri ON ri.Recipes_ID = r.id
                JOIN Ingredients i ON i.id = ri.Ingredients_ID
                WHERE LOWER(i.name) LIKE '%yeast%'
                   OR LOWER(i.name) LIKE '%sourdough%'
                   OR LOWER(i.name) LIKE '%starter%'
                   OR LOWER(i.name) LIKE '%baking powder%'
                   OR LOWER(i.name) LIKE '%baking soda%'
                   OR LOWER(i.name) LIKE '%cream of tartar%'
                GROUP BY e.id, e.name, agent_type
                ORDER BY e.id
                """;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement eraStatement = connection.prepareStatement(eraSql);
             ResultSet eras = eraStatement.executeQuery()) {

            while (eras.next()) {
                Map<String, Integer> counts = new LinkedHashMap<>();
                for (String agentType : agentTypes) {
                    counts.put(agentType, 0);
                }
                timeline.put(eras.getString("name"), counts);
            }

            try (PreparedStatement agentStatement = connection.prepareStatement(agentSql);
                 ResultSet result = agentStatement.executeQuery()) {

                while (result.next()) {
                    String eraName = result.getString("era_name");
                    String agentType = result.getString("agent_type");
                    int recipeCount = result.getInt("recipe_count");

                    if (timeline.containsKey(eraName) && timeline.get(eraName).containsKey(agentType)) {
                        timeline.get(eraName).put(agentType, recipeCount);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return timeline;
    }

    public Map<String, Integer> getGlobalArchiveStats() {
        Map<String, Integer> stats = new HashMap<>();

        String totalRecipesSql = "SELECT COUNT(*) FROM Recipes";
        String totalErasSql = "SELECT COUNT(*) FROM Eras";

        try (Connection conn = DatabaseManager.getConnection()) {

            //Total Recipe Count
            try (PreparedStatement pst = conn.prepareStatement(totalRecipesSql);
                 ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    stats.put("totalRecipes", rs.getInt(1));
                }
            }

            //Total Era Count
            try (PreparedStatement pst = conn.prepareStatement(totalErasSql);
                 ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    stats.put("totalEras", rs.getInt(1));
                }
            }

        } catch (Exception e) {
            System.err.println("Error fetching global archive stats: " + e.getMessage());
            e.printStackTrace();
        }

        return stats;
    }

    public List<Map<String, Object>> getSweetenerUsageByEra() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = """
            SELECT
                everything.era_name,
                everything.sweetener_name,
                COALESCE(real_counts.usage_count, 0) AS usage_count
            FROM (
                SELECT eras.name AS era_name, distinct_sweeteners.name AS sweetener_name, eras.era_order AS everything_eraorder
                FROM Eras eras
                CROSS JOIN (
                    SELECT DISTINCT name
                    FROM Ingredients
                    WHERE category = 'sweetener'
                ) AS distinct_sweeteners
            ) AS everything
            LEFT JOIN (
                SELECT eras.name AS era_name, i.name AS sweetener_name, COUNT(*) AS usage_count
                FROM Eras eras
                JOIN Recipes r ON eras.id = r.era_id
                JOIN Recipe_Ingredients ri ON r.id = ri.Recipes_ID
                JOIN Ingredients i ON ri.Ingredients_ID = i.id
                WHERE i.category = 'sweetener'
                GROUP BY eras.name, i.name
            ) AS real_counts
            ON everything.era_name = real_counts.era_name
               AND everything.sweetener_name = real_counts.sweetener_name
            ORDER BY everything_eraorder, everything.sweetener_name
            """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("era_name", rs.getString("era_name"));
                row.put("sweetener_name", rs.getString("sweetener_name"));
                row.put("usage_count", rs.getInt("usage_count"));
                results.add(row);
            }
        } catch (Exception e) {
            System.err.println("Error fetching sweetener usage: " + e.getMessage());
            e.printStackTrace();
        }

        return results;
    }

    public Map<String, Integer> getIngredientCategoryBreakdown(String eraName) {
        Map<String, Integer> breakdown = new LinkedHashMap<>();
        String sql = """
            SELECT i.category, COUNT(*) AS usage_count
            FROM Eras e
            JOIN Recipes r ON e.id = r.era_id
            JOIN Recipe_Ingredients ri ON r.id = ri.Recipes_ID
            JOIN Ingredients i ON ri.Ingredients_ID = i.id
            WHERE e.name = ?
            AND i.category IS NOT NULL
            GROUP BY i.category
            ORDER BY usage_count DESC
            """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eraName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    breakdown.put(rs.getString("category"), rs.getInt("usage_count"));
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching ingredient breakdown: " + e.getMessage());
            e.printStackTrace();
        }

        return breakdown;
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
