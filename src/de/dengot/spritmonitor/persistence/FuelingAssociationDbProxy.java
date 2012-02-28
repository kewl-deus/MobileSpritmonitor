package de.dengot.spritmonitor.persistence;

import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.model.FuelingAssociation;

import java.util.List;

public class FuelingAssociationDbProxy implements FuelingAssociation {

    private List<Fueling> cache;

    public List<Fueling> getFuelings() {
        return null;
    }

    public void addFueling(Fueling fueling) {

    }
}
