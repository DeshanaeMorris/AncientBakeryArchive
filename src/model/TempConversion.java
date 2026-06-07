package model;

public class TempConversion {

    private int temperatureId;
    private int recipeId;
    private String historicalDescription;
    private double fahrenheit;
    private double celsius;

    public TempConversion() {
    }

    public int getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(int temperatureId) {
        this.temperatureId = temperatureId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getHistoricalDescription() {
        return historicalDescription;
    }

    public void setHistoricalDescription(String historicalDescription) {
        this.historicalDescription = historicalDescription;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }
}