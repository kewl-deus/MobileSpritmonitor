package de.dengot.spritmonitor.io;

import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.persistence.repository.FuelingRepository;

public class FuelingDbWriter implements FuelingWriter{

    private FuelingRepository repository;
    private long vehicleId;

    public FuelingDbWriter(FuelingRepository repository, long vehicleId) {
        this.repository = repository;
        this.vehicleId = vehicleId;
    }

    @Override
    public void writeFueling(Fueling fueling) {
        fueling.setVehicleId(vehicleId);
        repository.save(fueling);
    }
}
