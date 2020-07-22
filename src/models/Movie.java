package models;

import controllers.Validation;

public class Movie {
    private int id;
    private String name;
    private String type;
    private String timeSlot;
    private String language;
    private String subtitle;
    private String classification;
    int result = 0;

    public Movie(int id, String name, String type, String timeSlot, String language, String subtitle, String classification) {
        setId(id);
        setName(name);
        setType(type);
        setTimeSlot(timeSlot);
        setLanguage(language);
        setSubtitle(subtitle);
        setClassification(classification);
    }

    public Movie() {

    }

    public int setId(int id) {
        if (Validation.isValidId(Integer.toString(id))) {
            this.id = id;
            result = 1;
        }
        return result;
    }

    public int setName(String name) {
        if (Validation.isValidName(name)) {
            this.name = name;
            return result = 1;
        } else
            return result;
    }

    public int setType(String type) {
        if (Validation.isValidType(type)) {
            this.type = type;
            return result = 1;
        } else return result;
    }

    public void setTimeSlot(String timeSlot) {
        if (Validation.isValidShowTime(timeSlot))
            this.timeSlot = timeSlot;
    }

    public int setLanguage(String language) {
        if (Validation.isValidType(language)) {
            this.language = language;
            result = 1;
        }
        return result;
    }

    public int setSubtitle(String subtitle) {
        if (Validation.isValidType(subtitle)) {
            this.subtitle = subtitle;
            result = 1;
        }
        return result;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getLanguage() {
        return language;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getClassification() {
        return classification;
    }

}
