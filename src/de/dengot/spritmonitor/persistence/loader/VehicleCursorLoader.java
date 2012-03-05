package de.dengot.spritmonitor.persistence.loader;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import de.dengot.spritmonitor.persistence.repository.VehicleRepository;

public class VehicleCursorLoader extends CursorLoader {

    public static final String TAG = VehicleCursorLoader.class.getSimpleName();

    private VehicleRepository repository;

    public VehicleCursorLoader(Context context) {
        super(context);
        repository = new VehicleRepository(getContext());
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor =  repository.findAll();
        if (cursor != null) {
            // Ensure the cursor window is filled
            int count = cursor.getCount();
            Log.v(TAG, "count = " + count);
        }
        return cursor;
    }


}
