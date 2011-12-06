package de.dengot.spritmonitor.model;

import java.util.List;

public interface Vehicle {

    void addFueling(Fueling fueling);

    List<Fueling> getFuelings();

    String getName();

    void setName(String name);

    int getOdometer();

    int getTotalDistance();

    float getTotalQuantity();

    float getTotalCosts();

    float getAverageConsumption();

    float getAverageUnitPrice();
}
