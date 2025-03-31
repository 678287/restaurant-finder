package no.hvl.dat109.group3.model;
/**
 * An entity class for a POJO to be used when converting from JSON to POJO
 */
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
    private DisplayName displayName;  // Use DisplayName class instead of String
    
    @JsonProperty("cost")
    private String cost; 

    @JsonProperty("foodType")
    private String foodType; 

    @JsonProperty("visitorCount")
    private String visitorCount; 

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
    
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(String visitorCount) {
        this.visitorCount = visitorCount;
    }
}
