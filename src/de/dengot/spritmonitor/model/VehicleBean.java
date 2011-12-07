package de.dengot.spritmonitor.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VehicleBean extends IdentifyableBean implements Serializable, Vehicle {

    private String name;

    private List<Fueling> fuelings;

    public VehicleBean() {
        this.fuelings = new LinkedList<Fueling>();
    }

    public VehicleBean(String name) {
        this();
        this.name = name;
    }

    public void addFueling(Fueling fueling) {
        this.fuelings.add(fueling);
    }

    public List<Fueling> getFuelings() {
        return fuelings;
    }

    public void setFuelings(List<Fueling> fuelings){
        this.fuelings = fuelings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOdometer() {
        if (fuelings.isEmpty()) return 0;
        Collections.sort(fuelings);
        return fuelings.iterator().next().getOdometer();
    }

    public int getTotalDistance() {
        if (fuelings.isEmpty()) return 0;
        int sum = 0;
        for (Fueling fueling : fuelings) {
            sum += fueling.getDistance();
        }
        return sum;
    }

    public float getTotalQuantity() {
        if (fuelings.isEmpty()) return 0;
        float sum = 0;
        for (Fueling fueling : fuelings) {
            sum += fueling.getQuantity();
        }
        return sum;
    }

    public float getTotalCosts() {
        if (fuelings.isEmpty()) return 0;
        float sum = 0;
        for (Fueling fueling : fuelings) {
            sum += fueling.getCost();
        }
        return sum;
    }

    public float getAverageConsumption() {
        if (fuelings.isEmpty()) return 0;
        float sum = 0;
        for (Fueling fueling : fuelings) {
            sum += fueling.getConsumption();
        }
        return sum / fuelings.size();
    }

    public float getAverageUnitPrice() {
        if (fuelings.isEmpty()) return 0;
        float sum = 0;
        for (Fueling fueling : fuelings) {
            sum += fueling.getUnitPrice();
        }
        return sum / fuelings.size();
    }

    @Override
    public String toString() {
        return getName();
    }
}
