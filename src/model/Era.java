package model;

public class Era {
    private int eraId;
    private String eraName;
    private int startYear;
    private int endYear;
    private String description;

    public Era() {
    }

    public int getEraId() {
        return eraId;
    }

    public void setEraId(int eraId) {
        this.eraId = eraId;
    }

    public String getEraName() {
        return eraName;
    }

    public void setEraName(String eraName) {
        this.eraName = eraName;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}