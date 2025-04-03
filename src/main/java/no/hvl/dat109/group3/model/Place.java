package no.hvl.dat109.group3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    @JsonProperty("formattedAddress")
    private String address;

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("primaryType")
    private String primaryType;

    @JsonProperty("displayName")
    private DisplayName displayName;
    
    @JsonProperty("priceLevel")
    private String priceLevelString;

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public DisplayName getDisplayName() {
        return displayName;
    }

    public void setDisplayName(DisplayName displayName) {
        this.displayName = displayName;
    }
    
    public void setPriceLevel(String priceLevel) {
        this.priceLevelString = priceLevel;
    }
    
    public String getPriceLevel1() {
        return priceLevelString;
    }
    

    public Integer getPriceLevel() {
        if (priceLevelString == null) return null;
        return switch (priceLevelString) {
            case "PRICE_LEVEL_INEXPENSIVE" -> 1;
            case "PRICE_LEVEL_MODERATE" -> 2;
            case "PRICE_LEVEL_EXPENSIVE" -> 3;
            case "PRICE_LEVEL_VERY_EXPENSIVE" -> 4;
            default -> null;
        };
    }

    // Helper method for price level description
    public String getPriceLevelDescription() {
        Integer level = getPriceLevel();
        if (level == null) return "Ikke tilgjengelig";
        return switch (level) {
            case 1 -> "Billig ($)";
            case 2 -> "Moderat ($$)";
            case 3 -> "Dyrt ($$$)";
            case 4 -> "Veldig dyrt ($$$$)";
            default -> "Ukjent prisnivå";
        };
    }

}
