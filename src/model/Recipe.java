package model;

public class Recipe {

    private int recipeId;
    private String recipeName;
    private String originalInstructions;
    private String modernInstructions;
    private String historicalNotes;
    private int eraId;
    private int categoryId;
    private int sourceId;
    private int userId;

    public Recipe() {
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getOriginalInstructions() {
        return originalInstructions;
    }

    public void setOriginalInstructions(String originalInstructions) {
        this.originalInstructions = originalInstructions;
    }

    public String getModernInstructions() {
        return modernInstructions;
    }

    public void setModernInstructions(String modernInstructions) {
        this.modernInstructions = modernInstructions;
    }

    public String getHistoricalNotes() {
        return historicalNotes;
    }

    public void setHistoricalNotes(String historicalNotes) {
        this.historicalNotes = historicalNotes;
    }

    public int getEraId() {
        return eraId;
    }

    public void setEraId(int eraId) {
        this.eraId = eraId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}