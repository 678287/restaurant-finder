package no.hvl.dat109.group3.model;
/**
 * An entity class for a POJO to be used when converting from JSON to POJO
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty("id")
    private String id;

    @JsonProperty("formattedAddress")
    private String address;

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("primaryType")
    private String primaryType;

    @JsonProperty("displayName")
    private DisplayName displayName;  // Use DisplayName class instead of String
    
    @JsonProperty("priceRange")
    private PriceRange priceRange;

    @JsonProperty("location")
    private Location location;

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getLatitude() {
        return (location != null) ? location.getLatitude() : 0.0;
    }

    public double getLongitude() {
        return (location != null) ? location.getLongitude() : 0.0;
    }
}