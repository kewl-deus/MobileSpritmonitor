package de.dengot.spritmonitor.model;

import java.util.Date;

public interface Fueling extends  Identifyable, Comparable<Fueling>{

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

    void setFillup(boolean fillup);

    boolean isFillup();

    long getVehicleId();

    void setVehicleId(long vehicleId);

}
