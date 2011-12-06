package de.dengot.spritmonitor.model;

import java.util.Date;

public interface Fueling extends Comparable<Fueling>{

    float getCost();

    void setCost(float cost);

    Date getFilldate();

    void setFilldate(Date filldate);

    int getDistance();

    void setDistance(int distance);

    int getOdometer();

    void setOdometer(int odometer);

    float getQuantity();

    void setQuantity(float quantity);

    float getConsumption();

    float getUnitPrice();
}
