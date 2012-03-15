package de.dengot.spritmonitor.model;

import java.util.List;

public interface Vehicle extends Identifyable {

    String getName();

    void setName(String name);

    String getExternalId();

    void setExternalId(String externalId);
    
    int getOdometer();

    int getTotalDistance();

    float getTotalQuantity();

    float getTotalCosts();

    float getAverageConsumption();

    float getAverageUnitPrice();
}
