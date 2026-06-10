package model;

public class Recipe {
    private int id;
    private String name;
    private int eraId;
    private String originalText;
    private String modernizedText;
    private String temperatureDescription;
    private int basePortion;
    private String source;
    private String imageUrl;

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEraId() {
        return eraId;
    }

    public void setEraId(int eraId) {
        this.eraId = eraId;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getModernizedText() {
        return modernizedText;
    }

    public void setModernizedText(String modernizedText) {
        this.modernizedText = modernizedText;
    }

    public String getTemperatureDescription() {
        return temperatureDescription;
    }

    public void setTemperatureDescription(String temperatureDescription) {
        this.temperatureDescription = temperatureDescription;
    }

    public int getBasePortion() {
        return basePortion;
    }

    public void setBasePortion(int basePortion) {
        this.basePortion = basePortion;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}