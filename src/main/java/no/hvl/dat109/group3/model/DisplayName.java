package no.hvl.dat109.group3.model;
/**
 * An entity class to handle the format of DisplayName when converting from JSON to POJO
 */

public class DisplayName {
    private String text;
    private String languageCode;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
