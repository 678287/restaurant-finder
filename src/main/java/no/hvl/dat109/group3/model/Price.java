package no.hvl.dat109.group3.model;
/**
 * An entity class to handle the format of Price when converting from JSON to POJO
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    @JsonProperty("units")
    private String units;
    
    @JsonProperty("currencyCode")
    private String currencyCode;

    
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getNumericValue() {
        try {
            return Double.parseDouble(units);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
