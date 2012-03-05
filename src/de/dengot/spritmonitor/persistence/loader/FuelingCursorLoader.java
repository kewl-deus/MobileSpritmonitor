package de.dengot.spritmonitor.persistence.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import de.dengot.spritmonitor.persistence.repository.FuelingRepository;

public class FuelingCursorLoader extends CursorLoader{
    
    public static final String TAG = FuelingCursorLoader.class.getSimpleName();
    
    private FuelingRepository repository;
    private long vehicleId;

    public FuelingCursorLoader(Context context, long vechileId) {
        super(context);
        this.repository = new FuelingRepository(context);
        this.vehicleId = vechileId;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = repository.findByVehicle(vehicleId);
        if (cursor != null) {
            // Ensure the cursor window is filled
            int count = cursor.getCount();
            Log.v(TAG, "count = " + count);
        }
        return cursor;
    }
}
