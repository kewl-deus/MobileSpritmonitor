package de.dengot.spritmonitor.persistence.loader;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import de.dengot.spritmonitor.persistence.VehicleRepository;

public class VehicleCursorLoader extends AsyncTaskLoader<Cursor> {

    VehicleRepository repository;

    public VehicleCursorLoader(Context context) {
        super(context);
        repository = new VehicleRepository(getContext());
    }

    @Override
    public Cursor loadInBackground() {
        return repository.findAll();
    }


}
