package dea.ancientbakery;

import dea.ancientbakery.features.RecipeScaler;
import dea.ancientbakery.features.ThermalConverter;
import dea.ancientbakery.features.UnitConverter;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField originalAmountField;

    @FXML
    private TextField multiplierField;

    @FXML
    private Label scaledAmountResult;

    @FXML
    private TextField convertAmountField;

    @FXML
    private ComboBox<String> convertFromUnitBox;

    @FXML
    private Label convertedAmountResult;

    @FXML
    private TextField heatTermField;

    @FXML
    private Label thermalResult;

    @FXML
    public void initialize() {
        convertFromUnitBox.getItems().addAll("g to oz", "oz to g", "ml to cups", "cups to ml");
        convertFromUnitBox.setValue("g to oz");
    }

    @FXML
    protected void onScaleButtonClick() {
        try {
            double originalAmount = Double.parseDouble(originalAmountField.getText());
            double multiplier = Double.parseDouble(multiplierField.getText());

            double scaledAmount = RecipeScaler.scaleAmount(originalAmount, multiplier);
            scaledAmountResult.setText("Scaled amount: " + format(scaledAmount));
        } catch (NumberFormatException e) {
            scaledAmountResult.setText("Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            scaledAmountResult.setText(e.getMessage());
        }
    }

    @FXML
    protected void onConvertButtonClick() {
        try {
            double amount = Double.parseDouble(convertAmountField.getText());
            String conversionType = convertFromUnitBox.getValue();

            double convertedAmount = switch (conversionType) {
                case "g to oz" -> UnitConverter.gramsToOunces(amount);
                case "oz to g" -> UnitConverter.ouncesToGrams(amount);
                case "ml to cups" -> UnitConverter.millilitersToCups(amount);
                case "cups to ml" -> UnitConverter.cupsToMilliliters(amount);
                default -> amount;
            };

            String convertedUnit = switch (conversionType) {
                case "g to oz" -> "oz";
                case "oz to g" -> "g";
                case "ml to cups" -> "cups";
                case "cups to ml" -> "ml";
                default -> "";
            };

            convertedAmountResult.setText("Converted amount: " + format(convertedAmount) + " " + convertedUnit);
        } catch (NumberFormatException e) {
            convertedAmountResult.setText("Please enter a valid number.");
        }
    }

    @FXML
    protected void onThermalButtonClick() {
        String historicalTerm = heatTermField.getText();
        thermalResult.setText(ThermalConverter.convertHeatTerm(historicalTerm));
    }

    private String format(double value) {
        return String.format("%.2f", value);
    }
}
