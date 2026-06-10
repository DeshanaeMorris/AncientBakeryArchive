package dea.ancientbakery.features;


public class ThermalConverter {

    public static String convertHeatTerm(String historicalTerm) {
        if (historicalTerm == null || historicalTerm.isBlank()) {
            return "No historical heat term was entered.";
        }

        String term = historicalTerm.toLowerCase().trim();

        return switch (term) {
            case "low fire", "gentle fire", "cool oven" -> "Approximately 250°F - 300°F";
            case "moderate fire", "moderate oven", "medium heat" -> "Approximately 325°F - 375°F";
            case "hot fire", "hot oven", "strong heat" -> "Approximately 400°F - 450°F";
            case "bake under wood", "under wood", "wood fire" -> "Approximately 350°F - 400°F";
            default -> "No modern temperature estimate found.";
        };
    }


    public static String[] getAcceptedHistoricalTerms() {
        return new String[]{
                "low fire / gentle fire / cool oven",
                "moderate fire / moderate oven / medium heat",
                "hot fire / hot oven / strong heat",
                "bake under wood / under wood / wood fire"
        };
    }
}
