package de.dengot.spritmonitor.model;

import java.util.Collections;
import java.util.List;

public class VehicleDataCalculator {

    private List<Fueling> fuelings;

    public VehicleDataCalculator(List<Fueling> fuelings) {
        this.fuelings = fuelings;
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
}
