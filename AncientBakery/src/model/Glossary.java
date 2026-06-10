package model;

public class Glossary {
    private int id;
    private String term;
    private String definition;
    private String modernSubstitute;

    public Glossary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getModernSubstitute() {
        return modernSubstitute;
    }

    public void setModernSubstitute(String modernSubstitute) {
        this.modernSubstitute = modernSubstitute;
    }
}