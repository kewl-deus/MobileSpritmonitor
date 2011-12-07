package de.dengot.spritmonitor.model;

import java.util.Date;
import java.io.Serializable;

public class FuelingBean  extends IdentifyableBean implements Serializable, Fueling {
    
    private Date filldate;
    private int odometer;
    private int distance;
    private float quantity;
    private float cost;

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Date getFilldate() {
        return filldate;
    }

    public void setFilldate(Date filldate) {
        this.filldate = filldate;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return Verbrauch Liter/100km
     */
    public float getConsumption(){
        return getQuantity() / getDistance() * 100;
    }

    /**
     *
     * @return Literpreis
     */
    public float getUnitPrice(){
        return getCost() / getQuantity();
    }

    public int compareTo(Fueling other) {
        return this.getOdometer() - other.getOdometer();
    }
}
