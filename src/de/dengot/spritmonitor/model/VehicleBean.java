package de.dengot.spritmonitor.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VehicleBean extends IdentifyableBean implements Serializable, Vehicle {

    private String name;
    private String externalId;
    private int odometer;
    private int totalDistance;
    private float totalQuantity;
    private float totalCosts;
    private float averageConsumption;
    private float averageUnitPrice;

    public float getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(float averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public float getAverageUnitPrice() {
        return averageUnitPrice;
    }

    public void setAverageUnitPrice(float averageUnitPrice) {
        this.averageUnitPrice = averageUnitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public float getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(float totalCosts) {
        this.totalCosts = totalCosts;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public float getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(float totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public String toString() {
        return getName();
    }
}
