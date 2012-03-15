package de.dengot.spritmonitor.io;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

public class FuelingCsvImporter extends AsyncTaskLoader<Boolean> {

    //http://www.spritmonitor.de/de/betankungen/322172/csvexport.csv
    
    private long vehicleId;

    public FuelingCsvImporter(Context context, long vehicleId) {
        super(context);
        this.vehicleId = vehicleId;
    }

    @Override
    public Boolean loadInBackground() {
        String link = MessageFormat.format("http://www.spritmonitor.de/de/betankungen/{0}/csvexport.csv", vehicleId);
        try {
            URL url = new URL(link);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            reader.close();
        } catch (Exception e) {
            Log.e("CsvImport", MessageFormat.format("Import from '{0}' failed", link), e);
            return false;
        }
        return true;
    }
}
