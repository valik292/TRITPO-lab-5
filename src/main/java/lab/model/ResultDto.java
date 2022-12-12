package lab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultDto {
    @JsonProperty
    private List<Calculation> calculations;
    @JsonProperty
    private Double maxSpeed;
    @JsonProperty
    private Double minSpeed;
    @JsonProperty
    private Double sumOfSpeeds;

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public void setSumOfSpeeds(Double sumOfSpeeds) {
        this.sumOfSpeeds = sumOfSpeeds;
    }
}