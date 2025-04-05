package no.hvl.dat109.group3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceRange {
    @JsonProperty("startPrice")
    private Price startPrice;
    
    @JsonProperty("endPrice")
    private Price endPrice;

    
    public Price getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Price startPrice) {
        this.startPrice = startPrice;
    }

    public Price getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Price endPrice) {
        this.endPrice = endPrice;
    }

    // Helper methods to get numeric values
    public double getMin() {
        return startPrice != null ? startPrice.getNumericValue() : 0.0;
    }

    public double getMax() {
        return endPrice != null ? endPrice.getNumericValue() : 0.0;
    }

    @Override
    public String toString() {
        return getMin() + "-" + getMax();
    }
}
