package dea.ancientbakery.model;

public class RecipeIngredient {
    private int recipeId;
    private int ingredientId;
    private String ingredientName;
    private double quantity;
    private String unit;

    public RecipeIngredient(int recipeId, int ingredientId, String ingredientName, double quantity, String unit) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ingredientName + ": " + String.format("%.2f", quantity) + " " + unit;
    }
}
